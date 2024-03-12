Imagine a delivery executive called Aman standing idle in Koramangala somewhere when
suddenly his phone rings and notifies that he’s just been assigned a batch of 2 orders meant to
be delivered in the shortest possible timeframe.

All the circles in the figure above represent geo-locations :
● C1 : Consumer 1
● C2 : Consumer 2
● R1 : Restaurant C1 has ordered from. Average time it takes to prepare a meal is pt1 
● R2 : Restaurant C2 has ordered from. Average time it takes to prepare a meal is pt2
Since there are multiple ways to go about delivering these orders, your task is to help Aman
figure out the best way to finish the batch in the shortest possible time.
For the sake of simplicity, you can assume that Aman, R1 and R2 were informed about these
orders at the exact same time and all of them confirm on doing it immediately. Also, for travel
time between any two geo-locations, you can use the haversine formula with an average speed
of 20km/hr ( basically ignore actual road distance or confirmation delays everywhere although
the real world is hardly that simple ;) )

Marking criteria:
1. We need a running functional code where we can plug in values for the distances and
preparation time and get the output for the best path to take for the orders

2. Candidates should declare any assumptions they make at the start itself
3. Code should be of production quality and should take into consideration the best
practices of the language chosen
4. Code should be readable and modular.
5. Bonus points for adding tests and documentation
6. Bonus points for making it extensible for more restaurants and DE
