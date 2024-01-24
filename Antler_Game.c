//A digital version of an ancient antler throwing game created for CS 262 at George Mason University in C.

//Zack Laine
//CS 262, Section 003
//Project 1: Redacted at request of pressor
#include <stdio.h>
#include <stdlib.h>

void EnterRandomSeed();
int scoringPlayer(int randNum, int playerScore, int *playerBank, int *playerTurn, int *compTurn);
int scoringComp(int randNum, int compScore, int *compBank,  int *playerTurn, int *compTurn);
char keepThrowing();
int gameLoop(int *playerStake, int *compStake);
int playAgain();

//EnterRandomSeed
//Asks player for a seed and sets it to be used when random() is called
//Pre-condition: None
//Post-condition: None
void EnterRandomSeed()
{
	unsigned int myRandomSeed;
	printf("Please enter a random number seed: ");
	scanf(" %d", &myRandomSeed);
	srandom(myRandomSeed);
}

//scoringPlayer
//Handles the evaluation of a random number into a score, updates the score (and bank if necessary), and changes the turn if Suljie or Rikki Teline are encountered
//Pre-condition: Takes a random number, the player's score, a reference to the player's bank, a reference to the player's turn, and a reference to the computer's turn (turn values are booleans represented as ints)
//Post-condition: Updates turns and banks if necessary, and return's the score for the throw
int scoringPlayer(int randNum, int playerScore, int *playerBank, int *playerTurn, int *compTurn)
{
	if(randNum <= 15)
	{
		printf("You got a Rikki Teline. Your turn ends.\n");
		playerScore = 0;
		*playerTurn = 0;
		*compTurn = 1;
		printf("----------------------------------------------------------------------------------------\n");
	}
	else if(randNum >= 16 && randNum <= 30)
	{
		printf("You got a Suljie Teline. You add %d to your bank.\n", playerScore);
		*playerBank += playerScore;
		playerScore = 0;
		*playerTurn = 0;
                *compTurn = 1;
		printf("----------------------------------------------------------------------------------------\n");
	}
	else if(randNum >= 31 && randNum <= 54)
	{
		playerScore = playerScore + 2;
		printf("You got two!\n");
		printf("You have %d points.\n", playerScore);
	}
	else if(randNum >= 55 && randNum <= 69)
	{
		playerScore = playerScore + 3;
		printf("You got three!\n");
		printf("You have %d points.\n", playerScore);
	}
	else if(randNum >= 70 && randNum <= 82)
	{
		playerScore = playerScore + 5;
		printf("You got five!\n");
		printf("You have %d points.\n", playerScore);
	}
	else if(randNum >= 83 && randNum <= 91)
	{
		playerScore = playerScore + 10;
		printf("You got ten!\n");
		printf("You have %d points.\n", playerScore);
	}
	else if(randNum >= 92 && randNum <= 99)
	{
		playerScore = playerScore + 15;
		printf("You got fifteen!\n");
		printf("You have %d points.\n", playerScore);
	}
	return playerScore;
}

//scoringComp
//Handles the evaluation of a random number into a score, updates the score (and bank if necessary), and changes the turn if Suljie or Rikki Teline are encountered
//Pre-condition: Takes a random number, the computer's score, a reference to the computer's bank, a reference to the player's turn, and a reference to the computer's turn (turn values are booleans represented as ints)
//Post-condition: Updates turns and banks if necessary, and return's the score for the throw
int scoringComp(int randNum, int compScore, int *compBank, int *playerTurn, int *compTurn)
{
	if(randNum <= 15)
	{
		printf("Computer got a Rikki Teline. Its turn ends.\n");
		compScore = 0;
		*playerTurn = 1;
		*compTurn = 0;
		printf("----------------------------------------------------------------------------------------\n");
	}
	else if(randNum >= 16 && randNum <= 30)
	{
		printf("Computer got a Suljie Teline. Its turn ends.\n");
		*compBank += compScore;
                compScore = 0;
		*playerTurn = 1;
                *compTurn = 0;
		printf("----------------------------------------------------------------------------------------\n");

	}
	else if(randNum >= 31 && randNum <= 54)
	{
		printf("Computer got two.\n");
		compScore = compScore + 2;
	}
	else if(randNum >= 55 && randNum <= 69)
	{
		printf("Computer got three.\n");
		compScore = compScore + 3;
	}
	else if(randNum >= 70 && randNum <= 82)
	{
		printf("Computer got five.\n");
		compScore = compScore + 5;
	}
	else if(randNum >= 83 && randNum <= 91)
	{
		printf("Computer got ten.\n");
		compScore = compScore + 10;
	}
	else if(randNum >= 92 && randNum <= 99)
	{
		printf("Computer got fifteen.\n");
		compScore = compScore + 15;
	}
	return compScore;
}

//keepThrowing
//Sees whether a player would like to end their turn, or keep throwing their antler
//Pre-condition: none
//Post-condition: Returns a user-entered character representing if they want to throw again or not
char keepThrowing()
{
	char throwAgain = 'n';
	printf("Would you like to throw again?\n"); 
	scanf(" %c", &throwAgain);

	return throwAgain;
}

//playAgain
//Handles whether the player wants to play again
//Pre-condition: none
//Post-condition: Returns an int being passed back representing a bool value for whether to start another game or not
int playAgain()
{
	char playAgain;
	printf("Would you like to play again?\n");
	scanf(" %c", &playAgain);
	if(playAgain == 'y' || playAgain == 'Y')
	{
		return 1;
	}
	else if(playAgain == 'n' || playAgain == 'N')
	{
		return 0;
	}
}

//gameLoop
//Primary loop for the program, handles each each game of Sarvi Peli
//Pre-condition: Takes reference values of the player and computer stakes
//Post-condition: Returns an int representing a bool of whether the player wants to play again or quit
int gameLoop(int *playerStake, int *compStake)
{
	int victory = 126;
	int playerScore = 0;
	int compScore = 0;
	int playerBank = 0;
	int compBank = 0;
	int myRandomNum;
	//used as bool values
	int playing = 1;
	int playerTurn = 0;
	int compTurn = 0;
	char goFirst = 'n';
	char throwAgain = 'y';

	printf("Would you like to go first?\n");
	scanf(" %c", &goFirst);

	if(goFirst == 'y' || goFirst == 'Y')
	{
		playerTurn = 1;
		compTurn = 0;
	}
	else if(goFirst == 'n' || goFirst == 'N')
	{
		compTurn = 1;
		playerTurn = 0;
	}

	while(playing == 1)
	{
		printf("Player stake is %d, comp stake is %d\n", *playerStake, *compStake);
		if(playerTurn == 1 && (playerBank + playerScore < victory))
		{
			printf("You have  %d points in the bank.\n", playerBank);
			myRandomNum = random()%100;
			playerScore = scoringPlayer(myRandomNum, playerScore, &playerBank,  &playerTurn, &compTurn);
			if(playerTurn == 1)
			{
				throwAgain = keepThrowing();
				if(throwAgain == 'n')
				{
					playerBank += playerScore;
					playerScore = 0;
					printf("You've banked %d points.\n", playerBank);
					playerTurn = 0;
					compTurn= 1;
					printf("----------------------------------------------------------------------------------------\n");
				}
			}
			else
			{
				playerBank += playerScore;
                                playerScore = 0;
				printf("You've banked %d points.\n", playerBank);
			}
		}
		else if(compTurn == 1 && (compBank + compScore < victory))
		{
			myRandomNum = random()%100;
			printf("The comp has %d points in the bank.\n", compBank);
			compScore = scoringComp(myRandomNum, compScore, &compBank, &playerTurn, &compTurn);
			printf("The computer has %d points.\n", compScore);
			if(compScore >= 20 || (compScore + compBank >= victory))
			{
				compBank += compScore;
				compScore = 0;
				printf("The computer banked %d points.\n", compBank);
				playerScore = 0;
				playerTurn = 1;
				compTurn= 0;
				 printf("----------------------------------------------------------------------------------------\n");
			}
		}

		if(playerBank >= victory)
		{
			*playerStake = *playerStake +1;
			*compStake = *compStake -1;
			playing = 0;
			printf("Congratulations!  You won!\n");
			printf("Your stake is %d!\n", *playerStake);
			printf("The computer's stake is %d.\n", *compStake);

		}

		if(compBank >= victory)
		{
			*compStake = *compStake +1;
			*playerStake = *playerStake -1;
			playing = 0;
			printf("Aww, you lost.\n");
			printf("Your stake is %d.\n", *playerStake);
			printf("The computer's stake is %d.\n", *compStake);
		}
	}

	return playAgain();

}

int main()
{
	char play = 'n';
	int playerStake = 10;
	int compStake = 10;
	EnterRandomSeed();
	printf("Hello there!\n");

	printf("Would you like to play Sarvi Peli?  \nWhen asked a question enter y for yes or  n for no.\n");
	scanf(" %c", &play);

	if(play == 'y' || play == 'Y')
	{
		int quit = 1;
		while(quit != 0 && playerStake > 0 && compStake > 0)
		{
			quit = gameLoop(&playerStake, &compStake);
		}
	}
	return 0;
}
