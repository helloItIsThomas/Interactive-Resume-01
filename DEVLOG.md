
# Dev Log

I guess I can put my notes in here
### Hello World


So what if we have a delay
between when the user moves the mouse and when MrLine moves.
then, in this gap, we can do things.
Maybe we can drop a point every N milliseconds..?
and lerp between these points? specifically, the contourT of these points.
Maybe if the mouse is moving, every N milliseconds a new point is being interpolated to.

Or maybe you have to click? And maybe that's by far the easiest way to go about this.
Maybe we have affordances for buttons as well.
Which would answer the question of how folks would know it's meant to be interacted with.
Ok perfect let's do this.

So, when the user clicks, it drops a point.
This point is then converted to the point on the contour that it is nearest to.
This nearest point is then converted to a contourT: Double.
Then, the current contourT: Double interpolates to this contourT: Double.
So, the mouse click should fire something. It can fire a number that increments from 0.0 to 1.0.
Then, this number can be plugged into a mix function that mixes one number to the other.



Ok next step is to get a semi-elegant way to mix the drawPos from the currentPos to the targetPos