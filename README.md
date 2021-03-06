# Voronoi_Diagram

This repository contains a simple implementation of 2D Voronoi Diagram calculated for three random points. Points always form acute triangle.

## Table of contents
* [Introduction](#introduction)
* [Technologies](#technologies)
* [Sources](#sources)

## Introduction
Algorithm at the beginning randomly generates points on the board. At the same time, it is verified that the points are not too close to each other.

![obraz](https://user-images.githubusercontent.com/77791657/173353858-1aa32ffc-4ef5-48ef-9563-8395f5fd6498.png)

Next, objects representing lines between points and the centers of the lines are created.

![obraz](https://user-images.githubusercontent.com/77791657/173354670-4d7ccf7a-ae45-4cf7-9f88-606f273d189e.png)

Parametres of the circle described on this triangle are determined. 

![obraz](https://user-images.githubusercontent.com/77791657/173359624-ad486fca-f44d-4aa8-8aae-a6581ea72913.png)

The next step is to determine the connections between the centers of the line and the center of the circle.

![obraz](https://user-images.githubusercontent.com/77791657/173357123-493d0484-2c62-45ca-8c41-02d2d918ded7.png)

To obtain a Voronoi diagram for an acute triangle, it is necessary to determine the points of intersection of the pink lines with the lines x = 0 and x = 500 (frame sizes).
If the intersection point is to the left of the center of the circle, then the line is routed to the intersection with the line x = 0, and the left side of the line intersects with x = 500.

![obraz](https://user-images.githubusercontent.com/77791657/173357988-2b71ef04-cdc7-496d-8136-132a46eb3b8b.png)

At the end, you can cover the intersection points, the center of the circle and the sides of the triangle. Thus, we obtain the Voronoi diagram.

![obraz](https://user-images.githubusercontent.com/77791657/173358871-f360b199-d9ee-492a-bd06-e092c5302cc6.png)

## Technologies

The project was made entirely in Java. The paint method was used to draw elements, thanks to which the interface of the frame was designed.

**List of crucial classes:**
* java.util.Arrays (storing data)
* java.util.Random (random points)
* javax.swing (frame interface)
* java.awt (geometric structures)

## Sources

**Links to useful pages:**
* https://cartography-playground.gitlab.io/playgrounds/triangulation-delaunay-voronoi-diagram/#:~:text=Algorithm&text=The%20Voronoi%20diagram%20of%20P,%E2%89%A0i%20j%20%E2%89%A0%20i%20.
* https://github.com/MauriceGit/Delaunay_Triangulation
