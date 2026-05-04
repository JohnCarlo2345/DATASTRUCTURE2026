package FinalProject;

import java.util.*;
import java.io.*;

public class QuizzerGame {
    static Stack<Player> players = new Stack<>();
    static Stack<Question> questions = new Stack<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        loadData();
        mainMenu();
    }

    static void mainMenu() {
        while(true) {
            System.out.println("\n===== QUIZZER GAME =====");
            System.out.println("1. Player Registration");
            System.out.println("2. Question Bank");
            System.out.println("3. Play Game");
            System.out.println("4. Exit");
            System.out.print("Choice: ");
            switch(sc.nextInt()) {
                case 1: playerMenu(); break;
                case 2: questionMenu(); break;
                case 3: playGame(); break;
                case 4: saveData(); System.out.println("Saved! Goodbye!"); return;
                default: System.out.println("Invalid!");
            }
            sc.nextLine();
        }
    }

    static void playerMenu() {
        while(true) {
            System.out.println("\n--- PLAYERS ---");
            System.out.println("1.Add 2.Edit 3.Delete 4.List 5.Leaderboard 6.Back");
            System.out.print("Choice: ");
            int c = sc.nextInt(); sc.nextLine();
            if(c==6) break;
            switch(c) {
                case 1: System.out.print("Name: "); players.push(new Player(sc.nextLine(),0)); break;
                case 2: System.out.print("Index: "); int e=sc.nextInt(); sc.nextLine();
                        if(valid(e,players)) { System.out.print("New Name: "); players.get(e).name=sc.nextLine(); } break;
                case 3: System.out.print("Index: "); int d=sc.nextInt();
                        if(valid(d,players)) players.remove(d); break;
                case 4: for(int i=0;i<players.size();i++) System.out.println(i+". "+players.get(i)); break;
                case 5: List<Player> lb=new ArrayList<>(players); Collections.sort(lb,(a,b)->b.score-a.score);
                        lb.forEach(System.out::println); break;
            }
        }
    }

    static void questionMenu() {
        while(true) {
            System.out.println("\n--- QUESTIONS ---");
            System.out.println("1.Add 2.Edit 3.Delete 4.List 5.Back");
            System.out.print("Choice: ");
            int c = sc.nextInt(); sc.nextLine();
            if(c==5) break;
            switch(c) {
                case 1: System.out.print("Question: "); String q=sc.nextLine();
                        System.out.print("Answer: "); questions.push(new Question(q,sc.nextLine())); break;
                case 2: System.out.print("Index: "); int e=sc.nextInt(); sc.nextLine();
                        if(valid(e,questions)) { System.out.print("New Q: "); questions.get(e).question=sc.nextLine();
                        System.out.print("New A: "); questions.get(e).answer=sc.nextLine(); } break;
                case 3: System.out.print("Index: "); int d=sc.nextInt();
                        if(valid(d,questions)) questions.remove(d); break;
                case 4: for(int i=0;i<questions.size();i++) System.out.println(i+". "+questions.get(i)); break;
            }
        }
    }

    static void playGame() {
        if(players.isEmpty()||questions.isEmpty()){System.out.println("Add data first!");return;}
        System.out.println("\n--- SELECT PLAYER ---");
        for(int i=0;i<players.size();i++) System.out.println(i+". "+players.get(i).name);
        System.out.print("Index: "); int p=sc.nextInt(); sc.nextLine();
        if(!valid(p,players)) return;
        Player user = players.get(p);
        int score=0;
        List<Integer> used=new ArrayList<>();
        Random r=new Random();
        System.out.println("\n===== START =====");
        while(used.size()<questions.size()){
            int idx;
            do{ idx=r.nextInt(questions.size()); }while(used.contains(idx));
            used.add(idx);
            Question q=questions.get(idx);
            System.out.println("\n"+q.question);
            System.out.print("Answer: ");
            if(sc.nextLine().equalsIgnoreCase(q.answer)){System.out.println("CORRECT! +10"); score+=10;}
            else System.out.println("WRONG! Ans: "+q.answer);
        }
        user.score=score;
        System.out.println("\nFINAL SCORE: "+score);
    }

    static void saveData() {
        try{
            PrintWriter p=new PrintWriter("players.txt");
            for(Player pl:players) p.println(pl.name+","+pl.score);
            p.close();
            PrintWriter q=new PrintWriter("questions.txt");
            for(Question qu:questions) q.println(qu.question+","+qu.answer);
            q.close();
        }catch(Exception e){}
    }
    static void loadData() {
        try{
            Scanner f=new Scanner(new File("players.txt"));
            while(f.hasNextLine()){String[]s=f.nextLine().split(",");players.push(new Player(s[0],Integer.parseInt(s[1])));}
            f.close();
            f=new Scanner(new File("questions.txt"));
            while(f.hasNextLine()){String[]s=f.nextLine().split(",");questions.push(new Question(s[0],s[1]));}
            f.close();
        }catch(Exception e){}
    }
    static boolean valid(int i, Stack s) { return i>=0 && i<s.size(); }
}
