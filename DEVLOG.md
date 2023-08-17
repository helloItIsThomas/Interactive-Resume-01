
# Dev Log

I guess I can put my notes in here
### Hello World

next...
let's decide if we want to stick with the original plan,
or adjust based on Randy's idea.

The original idea was to have one section move, 
then the cleanBot goes to set it back in line,
then another,
etc. Let's do this.


We're going to consider eveerything as being applied to section 4.
In order to continue using the adsr filter,
when the section is selected, triggerOn is activated.
Then, when the cursor enters the section,
triggerOff is activated.
So, when triggerOn is activated currently,
we should change it to triggerOff().

Then triggerOn() should run once when a new section is selected.
Do I have a working selection mechanism? Let's check.

Ok pretty sure the selection mechanism is working, and only storing one selection at a time.
And I think the sections are respecting that.
