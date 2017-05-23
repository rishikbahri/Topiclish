import React, { Component } from 'react';

class TopicForm extends Component {
  constructor(props) {
    super(props);
    this.state = {value: '', hasError: false, maxLength: 255};
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }
  handleChange(event) {
    this.setState({value: event.target.value, hasError: false});
  }
  handleSubmit(){
    if(this.state.value.length===0){
      this.setState({hasError: true});
    }else{
      this.setState({value: ""});
      this.props.handleFormSubmission();
    }
  }
  render() {
    return (
      <div id="leftContainer">
        <p id="title">Topiclish</p>
        <div>
  					<h3>Create Topic</h3>
      				<div className={"form-group "+ (this.state.hasError? 'has-error': '')} id="textGroup">
      					<p className="formLabel">Topic Text <span id="textCharCounter">{this.state.value.length===0 ? "": "("+(this.state.maxLength-this.state.value.length)+" charecters remaining)"}</span></p>
      					<label className="control-label" htmlFor="topicName"><small id="textError">{this.state.hasError? 'This field cannot be empty': ''}</small></label>
      					<textarea value={this.state.value} onChange={this.handleChange} className="form-control" id="topicDesc" placeholder="Text" rows="3" maxLength="{this.state.maxLength}"></textarea>
      					 <br/>
      					 <button type="submit" id="submitBttn" className="btn btn-primary" onClick={this.handleSubmit}>Create</button>
  					</div>
  				</div>
      </div>
    );
  }
}

export default TopicForm;
