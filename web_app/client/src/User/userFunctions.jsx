import React from 'react';
import{decorate, observable, action} from 'mobx';

export default class userFunctions {
            name = "";
            age= "";
            gender= "";
            tableList= [];
            id="";
            isActive= true;
            showPopupForUpdate= false;
            showPopupForDelete=false;
            deleteData= [];
            isSpinning = false;
            isOpening=true;


// /*      onClick = () => {
//            fetch("http://localhost:8080/loginUser/getUser")
//            .then((res) => res.json())
//            .then((res) => {this.setState({ userList : res});})
//             {this.userList.map(list => {
//             if(list.name == this.userName && list.password == this.password) {
//                console.log(list.name);
//                console.log(list.password);
//                document.getElementById("incrt").innerText = "";
//                this.isOpening  = false;
//             } else {
//                 document.getElementById("incrt").innerText = "Incorrect login UserName/Password"
//                 return false
//             }
//            })}
//            } */

         cancelPopup = () => {
             this.showPopupForDelete = false
             this.showPopupForUpdate = false
         }
         yesPopupForDelete = () => {
                    console.log(this.deleteData.id)
                    fetch("http://localhost:8083/api/delete/"+this.deleteData.id)
                    if(this.deleteData.name == this.name && this.deleteData.age == this.age && this.deleteData.sex == this.gender) {
                       this.fieldEmpty();
                       document.querySelector('input[type=radio]:checked').checked = false;
                       this.isActive = true
                    }
            this.showPopupForDelete = false
         }

       displayAllUsers = () => {
       setTimeout(() => {
       fetch("http://localhost:8083/api/getAllUser")
             .then((res) => res.json())
             .then((res) => {
             this.tableList = res;
             })
           },1000);

       }
      deleteMethod = (listValue) => {
         this.deleteData = listValue
         this.showPopupForDelete = true
      }
       onChange = (e) => {
       this[e.target.name]=e.target.value;
            var reg = new RegExp('^[0-9]+$');
            if(!this.name == '') {
                  document.getElementById("nam_e").style.border = "0";
                  document.getElementById("nameErr").innerText ='';
              }
              if (!this.age == "" ) {
                  document.getElementById("ageError").style.border = "0";
                  document.getElementById("ageErr").innerText = '';
              }
             if(reg.test(this.age)){
                 document.getElementById("ageErr").innerText = '';
             }
             if(document.querySelector('input[type=radio]:checked').checked === true) {
                document.getElementById("genderErr").innerText = "";
             }
       }

       onSubmit = (event) => {
          var reg = new RegExp('^[0-9]+$');
          reg.test(this.age)
         if(this.name == "") {
            document.getElementById("nam_e").style.border = "2px solid red";
            document.getElementById("nameErr").innerText ="Name field should be required";
         }
         if (this.age == "" ) {
             document.getElementById("ageError").style.border = "2px solid red";
             document.getElementById("ageErr").innerText ="Age field should be required";
         }
         if(this.gender == "") {
             document.getElementById("genderErr").innerText ="Gender field should be required";
         } else {
          document.getElementById("genderErr").innerText = "";
         }
         if(!reg.test(this.age) && this.age !== "") {
              document.getElementById("ageError").style.border = "2px solid red";
              document.getElementById("ageErr").innerText ="Age field invalid";
         }
         if(this.name == "" || this.age == "" || this.gender == "" || !reg.test(this.age)) {
           return false
         }
          fetch("http://localhost:8083/api/createUser",{method: "POST",
                      headers : new Headers({'content-type' : 'application/json'}),
                      body:JSON.stringify({name : this.name, age : this.age,sex : this.gender}),
                      })
                      .then((res) => {res.json().then(res => console.log(res))})
                      .catch((res)=> {console.log(res)})
                      this.fieldEmpty();
                      document.querySelector('input[type=radio]:checked').checked = false;
     }
      handleSubmit = () => {
           this.fieldEmpty();
           document.querySelector('input[type=radio]:checked').checked = false;
           this.isActive = true;
        }

      editMethod = (listValue) => {
          this.fieldEmpty();
          var id = listValue.id;
          this.name = listValue.name;
          this.age = listValue.age;
          this.gender = listValue.sex;
          if(document.getElementById("male").value == listValue.sex) {
           document.getElementById("male").checked = true
          } else if(document.getElementById("female").value == listValue.sex) {
           document.getElementById("female").checked = true
          }
          this.id = listValue.id
          console.log(this.id);
          this.isActive = false;
          this.refs.dropdown.focus();
          document.getElementById("formId").focus();
      }
      saveObject = () => {
                    var reg = new RegExp('^[0-9]+$');
                    reg.test(this.age)
                    if(this.name == "") {
                       document.getElementById("nam_e").style.border = "2px solid red";
                       document.getElementById("nameErr").innerText ="Name field should be required";
                    }
                    if (this.age == "" ) {
                        document.getElementById("ageError").style.border = "2px solid red";
                        document.getElementById("ageErr").innerText ="Age field should be required";
                    }
                    if(!reg.test(this.age) && this.age !== "") {
                         document.getElementById("ageError").style.border = "2px solid red";
                         document.getElementById("ageErr").innerText ="Age field invalid";
                    }
                    if(this.name == "" || this.age == "" || this.gender == "" || !reg.test(this.age)) {
                      return false
                    }
                  this.showPopupForUpdate = true
      }
      yesPopUpForUpdate = () => {
                 fetch("http://localhost:8083/api/updateUser/"+this.id,{method: "POST",
                                          headers : new Headers({'content-type' : 'application/json'}),
                                          body:JSON.stringify({name : this.name, age : this.age,sex : this.gender}),
                                          }).then((res) => {res.json().then(res => console.log(res))})
                                          console.log(this.name)
                                           this.fieldEmpty();
                                           document.querySelector('input[type=radio]:checked').checked = false;
                                           this.isActive = true
                                           this.showPopupForUpdate = false
      }
      fieldEmpty = () => {
             this.name = "";
             this.age = "";
             this.gender = "";
             document.getElementById("nam_e").style.border = "0";
             document.getElementById("ageError").style.border = "0"
             document.getElementById("nameErr").innerText ="";
             document.getElementById("ageErr").innerText ="";
             document.getElementById("genderErr").innerText ="";
      }
}
decorate(userFunctions,{
      name:observable,
      age:observable,
      gender:observable,
      tableList:observable,
      id:observable,
      isActive:observable,
      showPopupForUpdate:observable,
      showPopupForDelete:observable,
      deleteData:observable,
      isSpinning:observable,
      isOpening:observable
})
