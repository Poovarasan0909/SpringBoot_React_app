import React,{Component} from 'react';
import LoginPage from './LoginPage';
import {observer} from "mobx-react";
import  Modal  from 'react-modal';
import LoginPageStore from "./LoginPageStore";
import UserDataTable from "./UserDataTable";
import { Audio,Oval } from 'react-loader-spinner';
import userFunctions from './userFunctions';
import './user.css';
import {decorate, observable, action} from "mobx"


class User extends React.Component {

          componentWillMount() {
               this.loginPageStore = new LoginPageStore();
               this.userFunctions = new userFunctions();
               this.userFunctions.displayAllUsers()
           }
//            componentWillUpdate() {
//                this.userFunctions.displayAllUsers()
//            }


    render(){
    console.log(this.loginPageStore.isLogin)
    const customStyles = { content: {
        position : 'fixed',
         width: '40%',
         top: '50%',
         left: '50%',
         right: 'auto',
         bottom: 'auto',
         marginRight: '-50%',
         transform: 'translate(-50%, -50%)',
         background: '#efefef',
         textAlign: 'center',
         borderRadius:'15px'
       }
     };
        return (
        <div>
            {!this.loginPageStore.isLogin ?
            <LoginPage userStore={this.loginPageStore} /> :
                 <div>
                    <br/>
                    <div id="formId" ref="dropdown">
                    {this.userFunctions.isActive ?  <h2>CREATE</h2>: <h2>UPDATE</h2>}
                        <span>
                          <input type = "text" id = "nam_e" name = "name" value = {this.userFunctions.name} onChange={this.userFunctions.onChange} placeholder="Name" />
                          <span id="nameErr"></span>
                        </span>
                        <br/><br/>
                        <span>
                          <input type = "text" id = "ageError" name = "age" value = {this.userFunctions.age} onChange={this.userFunctions.onChange} placeholder="Age" />
                          <span id ="ageErr"></span>
                        </span>
                        <br/><br/>
                        <div >
                         Gender : &nbsp;&nbsp;<input type = "radio" id = "male" name = "gender" value = "Male" onChange={this.userFunctions.onChange}/>
                          <label for = "gender">Male</label>
                          <input type = "radio" id = "female" name = "gender" value = "Female" onChange={this.userFunctions.onChange} />
                          <label for="gender">Female</label><br/>
                          <span id ="genderErr"></span>
                        </div>
                        <br/>
                        {this.userFunctions.isActive ?  <button id="submitButton"  type = "submit" onClick={this.userFunctions.onSubmit}><b>Submit</b></button>
                                             : <button id = "saveButton"  onClick={this.userFunctions.saveObject}><b>save</b></button>
                                                }&nbsp;&nbsp;&nbsp;
                        {this.userFunctions.isActive ?  null: <button id = "cancelButton"  onClick={this.userFunctions.handleSubmit}><b>cancel</b></button>}
                          <br/>

                    </div>
                 <h1></h1>
                   <Modal isOpen={this.userFunctions.showPopupForDelete} style={customStyles} ariaHideApp={false}>
                   <div className="popupCustom">
                           <button className="close" onClick={this.userFunctions.cancelPopup}><i class="material-icons">close</i></button>
                           <h3> Do you want to delete {this.userFunctions.deleteData.name}?</h3>
                           <button className= "popupButton" onClick={this.userFunctions.cancelPopup}>NO</button>&nbsp;&nbsp;&nbsp;&nbsp;
                           <button className= "popupButton"  onClick={this.userFunctions.yesPopupForDelete}>YES</button>
                     </div>
                    </Modal>

                    <Modal isOpen={this.userFunctions.showPopupForUpdate} style={customStyles} ariaHideApp={false}>
                            <button className="close" onClick={this.userFunctions.cancelPopup}><i class="material-icons">cancel</i></button>
                           <h3>Do you want to update? </h3>
                           <button className= "popupButton" onClick={this.userFunctions.cancelPopup}>NO</button>&nbsp;&nbsp;&nbsp;&nbsp;
                           <button className= "popupButton"  onClick={this.userFunctions.yesPopUpForUpdate}>YES</button>
                     </Modal>
                     {this.userFunctions.tableList == [] ? <div className="loading"><Oval height={80} width={80} color="#4fa94d" wrapperStyle={{}} wrapperClass="" visible={true} ariaLabel='oval-loading'
                                                             secondaryColor="#4fa94d"  strokeWidth={2}  strokeWidthSecondary={2}
                                                           /></div>
                                                 : <UserDataTable tableList = {this.userFunctions.tableList} deleteMethod = {this.userFunctions.deleteMethod} editMethod={this.userFunctions.editMethod}/> }
               </div>
                }
        </div>
        );
    }
}
export default observer(User);