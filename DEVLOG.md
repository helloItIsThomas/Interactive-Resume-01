
# Dev Log

I guess I can put my notes in here
### Hello World


And then perhaps the decay time can be dynamically affected by
the distance away from the inner rect while still in the outer rect.
 
I have some notes on what to do next on line 57
in Section.kt

Ok, the idea that the distance of the mouse from the inner rectangle
while it is within the outer rectangle,
mapping onto the speed of the "decay" (which is actually the release) of the filter,
seems to not be working, and my best guess is that once the trigger goes off,
the release it has been set at is static. The release can be changed dynamically,
but it seems that it can only dynamically change before it has been triggered off.
At least in the configuration I have now.
So I'm going to abandon this idea for now,
and move forward with just having two states, in and out.


## Remember, phaseAmt is not the thing that is directly changing the positions.
phaseAmt is how much mix there is between the origin position and the secondary position.