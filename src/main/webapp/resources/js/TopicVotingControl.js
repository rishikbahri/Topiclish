import React, { Component } from 'react';

class TopicVotingControl extends Component {
  render() {
    return (
      <div className="voteButtonGroup">
        <p className="voteCount">{this.props.voteCount} Votes</p>
        <div className="btn-group" role="group">
          <button className="btn btn-primary" aria-label="Left Align" onClick={() => this.props.handleTopicUpvote(this.props.topicId)}>
            <span className="glyphicon glyphicon-triangle-top" aria-label="Left Align"></span>Upvote
          </button>
          <button className="btn btn-default" aria-label="Left Align" onClick={this.props.handleTopicDownvote.bind(null,this.props.topicId)}>
            <span className="glyphicon glyphicon-triangle-bottom" aria-label="Left Align"></span>Downvote
          </button>
        </div>
      </div>
    );
  }
}

export default TopicVotingControl;
