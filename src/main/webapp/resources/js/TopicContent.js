import React, { Component } from 'react';

class TopicContent extends Component {
  render() {
    var topicDate = new Date(this.props.insertDatetime);
		var formattedDate = "Created on "+topicDate.getDate()+"/"+topicDate.getMonth()+"/"+topicDate.getFullYear()
							+" "+topicDate.getHours() + ":"
							+ topicDate.getMinutes() + ":" + topicDate.getSeconds();
    return (
      <div className="topicContent">
        <p>{this.props.topicDesc}</p>
        <small className="text-muted">Created on {formattedDate}</small>
      </div>
    );
  }
}

export default TopicContent;
