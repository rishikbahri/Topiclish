import React, { Component } from 'react';
import TopicVotingControl from './TopicVotingControl';
import TopicContent from './TopicContent';


class Topic extends Component {
  render() {
    return (
      <div className="topic">
        <div className="topicContainer">
          <TopicVotingControl topicId={this.props.topicId} topicNum={this.props.topicNum} handleTopicUpvote={this.props.handleTopicUpvote} handleTopicDownvote={this.props.handleTopicDownvote} voteCount={this.props.voteCount}/>
          <TopicContent topicDesc={this.props.topicDesc} insertDatetime={this.props.insertDatetime} />
        </div>
      </div>
    );
  }
}

export default Topic;
