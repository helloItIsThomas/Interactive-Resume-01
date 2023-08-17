
# Dev Log

I guess I can put my notes in here
### Hello World

Ok pretty sure the selection mechanism is working, and only storing one selection at a time.
And I think the sections are respecting that.

Ok I think it's time to get Mr Line going.

So at the moment,
the cursor is the only thing that affects when the movement stops.
I want Mr Line to also be able to affect when the movement stops.
So updateSelection should not only be happening where it is right now,
but also if MrLine gets to it.
Next step, lets see if I can package things any more cleanly, so hopefully i can just call one function to update everything.
Ok pretty sure I condensed everything as much as I can.

Now, Let's just have MrLine call updateSelection as well when his conditions are met.

# So, for MrLine
### In the intro method of section...
i could say MrLine.triggerOn()
and then the trigger would trigger MrLine to go from his current position
to his goal position.
well actually this isnt really the move
because I don't want to have it go back to the start.
I should do some thinking about how I want Mr. Line to act.
Maybe if there's time, he stays in the area to get the surrounding sections if they bundle up.
But, if the sections are spread apart enough, he will go there and then go home.
So the question now is, how would I que a handful of destinations before I release the trigger?
i probably don't want to use trigger in this case, it might get confusing.
I think i'm done for the day though. I'll think about this tomorrow.