import React, { Component } from 'react';

class TopicForm extends Component {
  constructor(props) {
    super(props);
    this.state = {value: '', hasError: false, maxLength: document.getElementById("react").getAttribute("supportedDescLen"), isEnterSubmit: false};
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleKeyPress = this.handleKeyPress.bind(this);
    this.toggleEnterSubmit = this.toggleEnterSubmit.bind(this);
  }
  handleChange(event) {
    this.setState({value: event.target.value, hasError: false});
  }
  handleSubmit(){
    if(this.state.value.length===0){
      this.setState({hasError: true});
    }else{
      this.setState({value: ''});
      this.props.handleFormSubmission();
    }
  }
  handleKeyPress(target) {
    if(target.charCode==13&&this.state.isEnterSubmit){
    	target.preventDefault();
    	this.handleSubmit();   
    }
  }
  toggleEnterSubmit(){
	  var toggleVal = (this.state.isEnterSubmit?false:true);
	  this.setState({isEnterSubmit: toggleVal});
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
      					<textarea value={this.state.value} onKeyPress={this.handleKeyPress} onChange={this.handleChange} className="form-control" id="topicDesc" placeholder="Text" rows="3" maxLength={this.state.maxLength}></textarea>
      					 <br/>
      					 <button type="submit" id="submitBttn" className="btn btn-primary" onClick={this.handleSubmit}>Create</button>
      					 <input type="checkbox" onClick={this.toggleEnterSubmit}/> Enter to submit
  					</div>
  				</div>
      </div>
    );
  }
}

export default TopicForm;
