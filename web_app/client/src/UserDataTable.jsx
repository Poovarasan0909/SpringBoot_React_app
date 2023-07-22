import React from 'react';
import Table from 'react-bootstrap/Table'
import Button from 'react-bootstrap/Button';
import {Icon} from 'semantic-ui-react'

export default class UserDataTable extends React.Component{

    render() {
        const styleLink = document.createElement("link");
        styleLink.rel = "stylesheet";
        styleLink.href =  "https://cdn.jsdelivr.net/npm/semantic-ui/dist/semantic.min.css";
        document.head.appendChild(styleLink);
         return(
             <table className="tableList">
                          <tr>
                               <th>Name</th>
                               <th>Age</th>
                               <th>Gender</th>
                               <th>Edit</th>
                               <th>Delete</th>
                          </tr>
                             {this.props.tableList.map(listValue =>
                             <tr>
                                   <td>{listValue.name}</td>
                                   <td>{listValue.age}</td>
                                   <td>{listValue.sex}</td>
                                   <td><span onClick={()=>this.props.editMethod(listValue)}><Icon enabled name='pencil' /></span></td>
                                   <td><span onClick={()=>this.props.deleteMethod(listValue)}><Icon enabled name='trash' /></span></td>
                                  </tr>
                             )}
                      </table>
             );
    }
}