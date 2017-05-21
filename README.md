# Topiclish

This code maintains a list of topics sorted by vote counts in descending order.[Click here for a live demo](https://topiclish.herokuapp.com/)

## Technology Stack
This is built on java with Spring MVC framework for the backend and JQuery & Bootstrap for the frontend.

## Properties
The following are configurable properties that can be set for the project in the /src/main/resources/topiclish.properties file
* supportedTopicDescLength: The maximum length of a topic that can be created (Default value 255).
* topTopicsToRetirieve: The number of top topics that need to be displayed (Default value 20).

## Data Structure
The topics are stored in an in memory data structure (not a relational databases) which is a singleton so as to maintain only one instance while the app is running.

The data structure consists of the following:
* HashMap: With the key as the topicId and value as the Topic object. This is used to retrieve the topics just based on their unique topicId
```
private HashMap<String,Topic> topicMap;
```

* TreeMap: With the key as the number of votes and value as a LinkedList of topicId's. This is used as a sort of index. We create buckets based on the number of votes a topic has and allocate a topic id to the bucket. The TreeMap data structure automatically sorts the data based on the key (number of votes) so it makes it easier to identify the top most topicId's
```
private TreeMap<Integer,LinkedList<String>> topicVoteIndex;
```

### DAO Logic
The DAO layer allows for the following key methods:

* createTopic: This method adds a new topic to the data structure by first adding it to the HashMap and then adding it to the TreeMap index in bucket 0
* updateTopicVote: This method takes in a topicId and what kind of voting operation needs to be done on it. This is a synchronized method so as to not allow concurrent modification of data. If an existing thread is using this method another thread trying to access this will be queued. 
  * This method first retrieves the topic from the HashMap based on its ID and updates the vote count for it.
  * It then reindexes the topic in the TreeMap by removing it from its original vote count bucket and placing it in the new vote count bucket. If the original bucket has no more topics in it the bucket is removed.
* getTopNTopics: This method takes in topNTopics (the number of top topics to retrieve) and returns them in a List of Topics: 
  * It loops through the TreeMap of indexes which will give it the list of topicIds of the topics with the top most votes. 
  * It then loops through the list of topicIds and retrieves them from the HashMap by passing the id. It then adds them in the topicList that has to be returned. 
  * Once topicList has reached its maxed limit based on topNTopics it breaks the outer loop and returns the List of topics
