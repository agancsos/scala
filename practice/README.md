# Practice

## Synopsis
This project is meant to prepare for the Meraki live coding session by practicing with data structures and sorting.

## Assumptions
1. I get invited for a live coding session.
2. The live coding session may or may not be based on finding the lowest, greatest, least, or most repeated values in a list.
3. The live coding session may not be based on memory manipulation.
4. The live coding session may deal with regex, but this doesn't require practice.
5. The live coding session may have a portion on converting an IP address to binary.
6. For the IP address conversion, the actual IP address doesn't matter, but it needs to convert to the correct binary value;
7. The live coding session may have a portion on access an API endpoint, but the endpoint may not need authentication.  This may not need practice in that case.

## Contents
All implementations produce the same highest and lowest values, but least and most repeated values might produce different results depending on the sorting algorithm used.

### Scala
This is the Scala implementation of the project as the language was an area of interest to learn.  Some key notes about this implementation include:
1. Least repeated is based on the highest key with the least repeats.  This is technically not wrong, but can be offthrowing.
2. Most repeated is based on the lowest key with the most repeats.  Again, this is technically not wrong, but looks wierd.

### Go
This is the Go implemenation of the project as this is the most recent language (other than Ruby) that feels most comfortable.  Some key notes about this implementation include:
1. Like with scala, least repeated is based on the highest key with the least repeats.  This is technically not wrong, but can be offthrowing.
2. Most repeated in this case is also based on the highest key with the most repeats.  As both chose the highest key, it looks less strange and can just be considered part of the design.

### Python
This is the Python implemenation of the project as this is the quickest language to implement smaller projects.  Some key notes about this implementation include:
1. Similar to Go, least repeated is based on the highest key with the least repeats.  This is technically not wrong, but can be offthrowing.
2. Also as with Go, Most repeated in this case is also based on the highest key with the most repeats.  As the majority of the languages produce this result, this is considered the expected result.

### Ruby
This is the Ruby implemenation of the project as this is one of the languages to implement smaller projects.  Some key notes about this implementation include:
1. It has a nifty way of sorting hashes, so we can get 100% accurate results.  
2. Unlike with the other languages that depend on the internal sorting algorithm or a custom implementation, Ruby uses a lambda-like syntax for sort by a specific field.

## Retrospective
1. The biggest issue I ran into is the results as each language has it's own implemenation of the sorting.  Ideally, I would implement my own algorithm to produce consistent results.
2. Another qualm I had during the project is that Go doesn't have a native way of sorting Maps, more specifically key vs value.  If it wasn't for the comfort level, I would not recommend it with a time constraint.
3. On an up note, I would say Python or Ruby are the way to go if the role is of great interest.  Don't worry about using a compiled language, focus on an OOP language that gets the job done. 

