# Tambola-Ticket-Generator

It generates a tambola/Bingo sheet with 6 tickets. Below mentioned are the constraints:
1. The numbers 1 to 90 are used once only. 
2. In the first column are the numbers 1 to 9, the second column has numbers 10 to 19, etc, all the way to the 9th column which has numbers 80 to 90 in it.
3. Every row must have exactly 5 numbers in it.
4. The 3 cell high column in each ticket must have between 1-3 numbers in it but never zero numbers.
5. In each 3 cell high column, the numbers are sorted with lower numbers higher than larger numbers.
6. All the numbers 1 to 90 are used only once in each set of 6 tickets.


<b>The logic is almost same as the comment of "Mark Henderson" on the website "http://www.fractalforums.com/non-fractal-related-chit-chat/90-number-bingo-ticket-algorithm/?PHPSESSID=19fb5b949dd49c4d16811fd775dcec9b" which says:</b>

The algorithm is split into 2 parts.

First, generate 6 valid sets of 15 numbers
Second, distribute a set of 15 numbers into a card.


<u>first part:</u>

1) assign one number from each of the 9 columns into each of the 6 cards.
after that, you'll have 3 numbers left in the first column, 5 in the last column, and 4 in every other column.

2) assign one of the numbers in the last column to a random card.

3) now make 4 passes over the remaining columns.  In each pass, assign 1 of the remaining numbers for that column to a random card, skipping cards that are already full or have 3 numbers from that column. In the first three passes, maxing out at 2 numbers per column instead of 3.
This prevents it from running into a corner case where it can't finish successfully, but reduces the chance of having a full column.

This satisfies the below requirements:
1) at least 1 number in each column on each card
2) at most 3 numbers in each column on each card
3) 15 numbers per card
4) all 90 numbers are used


<u>second part:</u>: to distribute a valid set of 15 numbers to a card.

it consists of three passes, construct one row on each pass
First check for a column that has to fit (3 numbers left on 1st row, 2 left on 2nd row, 1 left on last row).
Then just fill the card from the remaining numbers (checking to make sure not repeating a column)

