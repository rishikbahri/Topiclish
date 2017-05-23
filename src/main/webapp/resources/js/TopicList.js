import React, { Component } from 'react';
import Topic from './Topic';
import $ from 'jquery';

class TopicList extends Component {

  constructor(props) {
    super(props);
    this.state = { topics: [] };
    this.handleTopicUpvote = this.handleTopicUpvote.bind(this);
    this.handleTopicDownvote = this.handleTopicDownvote.bind(this);

  }
  componentDidMount() {
    this.loadTopicList();
  }
  loadTopicList(){
    $.ajax({
  	  type: "GET",
      url: "getTopTopics",
      dataType : 'json',
  	  success: function(result){
        this.setState({topics: result.data});
      }.bind(this)
    });
  }
  handleTopicUpvote(topicId){
    $.ajax({
  	  type: "PUT",
      url: "upvoteTopic/"+topicId,
      dataType : 'json',
  	  success: function(){
        this.loadTopicList();
      }.bind(this)
    });
  }
  handleTopicDownvote(topicId){
    $.ajax({
  	  type: "PUT",
      url: "downvoteTopic/"+topicId,
      dataType : 'json',
  	  success: function(){
        this.loadTopicList();
      }.bind(this)
    });
  }
  render() {
    return (
      <div id="rightContainer">
      {this.state.topics.length ? this.state.topics.map(topic =>
      <Topic key={topic.topicId} topicId={topic.topicId} handleTopicUpvote={this.handleTopicUpvote} handleTopicDownvote={this.handleTopicDownvote} topicDesc={topic.topicDesc} insertDatetime={topic.insertDatetime} voteCount={topic.voteCount} />
    ) : 'There are no topics' }
      </div>
    );
  }
}

export default TopicList;
