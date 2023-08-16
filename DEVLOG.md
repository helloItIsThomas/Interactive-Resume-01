
# Dev Log

I guess I can put my notes in here
### Hello World



Ok things are getting a bit complicated now.
But as of this commit everything is working,
and I think I understand what's going on.

For each section,
I have a variable called parentNumWords.
This variable is a list of integers.
The list's length is determined by the number of "sub-sections" in the given section.
A "sub-section" is how I'm calling each CustomText object.
CustomText holds a string and a style.
This is important because I am breaking up the drawing based on the number of sub-sections.

Also, I think I need to get the total number of words in a section in order to figure out
how to split the circle that each word will be traveling to.
The problem is, I am not drawing all the words from each section at once,
I am drawing all the words from each sub-section for each section.

So I have to figure out what this means next...

