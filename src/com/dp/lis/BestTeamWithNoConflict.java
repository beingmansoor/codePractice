package com.dp.lis;

import java.util.Arrays;
import java.util.Comparator;

/*
Problem Statement:
You are the manager of a basketball team. For the upcoming tournament, you want to choose the team with 
the highest overall score. The score of the team is the sum of scores of all the players in the team.
However, the basketball team is not allowed to have conflicts. A conflict exists if a younger player has
 a strictly higher score than an older player. A conflict does not occur between players of the same age.
 
Given two lists, scores and ages, where each scores[i] and ages[i] represents the score and age of the 
ith player, respectively, return the highest overall score of all possible basketball teams.

Example 1:
Input: scores = [1,3,5,10,15], ages = [1,2,3,4,5]
Output: 34
Explanation: You can choose all the players.

Example 2:
Input: scores = [4,5,6,5], ages = [2,1,2,1]
Output: 16
Explanation: It is best to choose the last 3 players. Notice that you are allowed to choose multiple people of 
the same age.

Example 3:
Input: scores = [1,2,3,5], ages = [8,9,10,1]
Output: 6
Explanation: It is best to choose the first 3 players.
 */
public class BestTeamWithNoConflict
{
	/*
    Characteristics of this problem:
    This problem involves finding the subset with maximum score that maintains the condition that
    no younger player can have strictly greater score than an older player.
    
    So this is a perfect use case of Longest Increasing Subsequence.
    
    Remember what we have learned in the Longest Increasing Subsequence chapter:
    Most problems where you are given an array (or list) of items and you'd have to find the largest 
    subset of  the items which maintains certain given condition could be solved using Longest 
    Increasing Subsequence technique.
    */
    public int bestTeamScore(int[] scores, int[] ages) {
        int len = scores.length;
        Player[] players = new Player[len];
        for (int i = 0; i < len; i++) {
            players[i] = new Player(ages[i], scores[i]);
        }
        // Logic of the sorting should be devised very thoughtfully.
        // for this problem for same age players,
        // players should be scorted in ascending order of 
        // score, so that when we are processing a player
        // we have the leeway to include all the players with same age
        // if they are eligible (i.e, if their score is higher or same
        // as the score of the younger players already in the LIS.)
        // The player with the highest score in the same age group has the 
        // chance of including most number of younger players as well as all the eligible
        // same age players. But if we keep same age player with lower score
        // score after a same age player with higher score in the sorted
        // array then even if the same age player with lower score can include all
        // the eligible same age players but will miss out on including 
        // many younger players with higher score.
        // So to maximum score that includes a certain age player
        // will be gotten only when we keep the player of that age who has 
        // higest score for that age at the end of that age group players.
        Arrays.sort(players, new Comparator<Player>() {
            public int compare(Player p1, Player p2) {
                if (p1.age != p2.age) {
                    return p1.age - p2.age;
                } else {
                    return p1.score - p2.score;
                }
            }
        });
        for (Player p : players) {
            System.out.println(p.age + "   " + p.score);
        }
        int maxScore = scores[0];
        int[] lis = new int[len]; // lis[i] maximum score that can be achieved in a 
            // group of players that includes player[i and inlcudes players only
            // from players[0...i]
        for (int i = 0; i < len; i++) {
            lis[i] = players[i].score;
        }
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {
                if (doesQualify(players[j], players[i])) {
                    lis[i] = Math.max(lis[i], lis[j] + players[i].score);
                }
            }
            maxScore = Math.max(maxScore, lis[i]);
        }
        return maxScore;
    }
    
    private boolean doesQualify(Player younger, Player older) {
        boolean sameAge = younger.age == older.age;
        boolean differentAge = (younger.age < older.age) && (younger.score <= older.score);
        return sameAge || differentAge;
    }
    
    class Player {
        public int score;
        public int age;
        public Player(int age, int score) {
            this.age = age;
            this.score = score;
        }
    }
}
