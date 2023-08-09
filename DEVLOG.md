
# Dev Log

I guess I can put my notes in here
### Hello World

ok so the next thing i want to do

let's get rid of the outer rectangle for now and just focus on if the mouse is in or outside of each section.

+++

Ok so now when the mouse enters the rectangle it prints true once.
when it exists, it prints false once.
Also, when it enters, it triggers the filter to trigger from 0.0 to 1.0 to 0.0, according to the adsr parameters.
It only triggers on enter, not on exit, and seems to wait until the decay starts to trigger again.
So i think with short decay it will never overlap itself.

What this means? I am not sure.

Also, each section now has their own tracker, which is good.
However, they all share the same 'this' which is the program, which is the clock i guess?
That seems good? but perhaps it's not? idk. just something to keep in mind.
Don't be too quick to change it if something is wrong tho. it's probably fine, i just dont understand the implications of it.

+++

Just to note, there is a global tracker called Global.tracker, or just tracker, and then there are individual trackers for each section, called sectionTracker.
Pretty sure Global.tracker I'm using the global tracker for MrLine, i probably eventually want to move it out of global and into MrLine.

+++

Also at some point each section probably wants unique parameters for their individual trackers.

## Remember, phaseAmt is not the thing that is directly changing the positions.
phaseAmt is how much mix there is between the origin position and the secondary position.