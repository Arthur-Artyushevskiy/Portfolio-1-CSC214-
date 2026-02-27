package org.example;

public class App {
  public String getGreeting() {
    return "Hello World!";
  }

  public static void main(String[] args) {
    TicTacToe game = new TicTacToe('X', 'O');
    game.run();
  }
}
