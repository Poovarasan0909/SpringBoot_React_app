import React from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import {observer} from "mobx-react";
import '../UserLogin.css';

class LoginPage extends React.Component{
    constructor(props) {
        super(props);
        this.store=this.props.userStore;
    }
    render() {
        let user = this.props.userStore;
        return(
            <div id="loginForm">
                <Form className="signIn">
                    <h3 align="center">Login Page</h3>
                    <Form.Group className="mb-3" controlId="formBasicEmail">
                        <Form.Label>Email address</Form.Label>
                        <Form.Control type="email" name="userName" onChange={this.store.setUserNameAndPassword} placeholder="Enter email"/>
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="fomBasicPassword">
                        <Form.Label>Password</Form.Label>
                        <Form.Control type="password" name="password" onChange={this.store.setUserNameAndPassword} placeholder="Password"/>
                    </Form.Group>
                    <Button  variant="success" onClick={() => this.store.validateCredentials()}>Submit</Button>
                </Form>
            </div>
        )
    }
}
export default observer(LoginPage);