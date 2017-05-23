'use strict';
import React, { Component } from 'react';
import ReactDOM from 'react-dom';

import TopicForm from './TopicForm';
import TopicList from './TopicList';
import $ from 'jquery';

class Container extends Component {
  constructor(props) {
    super(props);
    this.state = { topics: [] };
    this.handleFormSubmission = this.handleFormSubmission.bind(this);

  }
  handleFormSubmission() {
    $.ajax({
  	  type: "POST",
      url: "createTopic",
      dataType : 'json', 
      data: {topicDesc: $("#topicDesc").val()},
  	  success: function(){
        this._topicList.loadTopicList();
      }.bind(this)
    });
  }
  render() {
    return (
      <div id="container">
        <TopicForm handleFormSubmission={this.handleFormSubmission}/>
        <TopicList ref={(topicList) => { this._topicList = topicList; }} />
      </div>
    );
  }
}

ReactDOM.render(
  <Container />,
  document.getElementById('react')
);