import { Component, OnInit } from '@angular/core';
import {FormControl} from '@angular/forms';

import { MatDialog } from '@angular/material';

import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { LoginService } from '../login.service';
import subdomain from 'src/assets/jsonfiles/data2.json';
import skills from 'src/assets/jsonfiles/data4.json';
import roles from 'src/assets/jsonfiles/data3.json';

import { RegistrationServiceService } from '../registration-service.service';
import { Router } from '@angular/router';
import { ModalSuccessComponent } from '../modal-success/modal-success.component';

@Component({
  selector: 'app-service-provider-registration',
  templateUrl: './service-provider-registration.component.html',
  styleUrls: ['./service-provider-registration.component.css']
})
export class ServiceProviderRegistrationComponent implements OnInit {
 
  Domain = new FormControl();
  domainList: string[] = ['IT', 'TOURISM'];
  subdomain = new FormControl();
 
  skills = new FormControl();
  skillsList: any=skills;
  RolesList: any=roles;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  thirdFormGroup: FormGroup;
  isOptional = true;
  subDomainList:any=[];
skillSet:any=[];
i:any;

form: any = {};


errorMessage = '';
pass:string='';
conf:string;
  LoginService: any;



  constructor(private _formBuilder: FormBuilder,private registrationService:RegistrationServiceService,private dialog:MatDialog, private router: Router) { }
  getErrorFnameMessage(){
    // return this.firstFormGroup.controls.FirstName.hasError('required') ? 'You must enter a value':'';
  }
 
  getpassErrorMessage(){
    //return this.password.hasError('required') ? 'You must enter a value': '';
  }
 
  getconfirmErrorMessage(){
  //  if(this.password.value!=this.confirm.value) {
  //  return "Password Not Matching";
   // }
  }

  ngOnInit() {

    for (this.i in subdomain) {
      console.log(subdomain[this.i]);
      this.subDomainList[this.i]= subdomain[this.i];
      // console.log(this.subDomainList);
    }

    for (this.i in skills) {
      console.log(skills[this.i]);
      this.skillSet[this.i]= skills[this.i];
      // console.log(this.subDomainList);
    }

    this.firstFormGroup = this._formBuilder.group({
      FirstName: ['', Validators.required],
      LastName: ['', Validators.required]
  
  
    });
    this.secondFormGroup = this._formBuilder.group({
      email:['',Validators.email],
      password:['',Validators.minLength(8)],
      domainCtrl: ['', Validators.required]
     
       
      
       
 
    });
    this.thirdFormGroup = this._formBuilder.group({
      subdomain:[''],
      skill:[''],
      RoleCtrl: ['', Validators.required],
       chargePerHour: ['', Validators.required]


    });
  }
  onSubmit(){

    this.pass= this.secondFormGroup.controls.password.value;
    this.conf= this.secondFormGroup.controls.confirm.value;
  }
 
  register():void
  {
    console.log(this.form);
    let providerData ={
      name:this.firstFormGroup.controls.FirstName.value,

      emailId:this.secondFormGroup.controls.email.value,
      
      password:this.secondFormGroup.controls.password.value,
      
      domain:this.secondFormGroup.controls.domainCtrl.value,
      
      subDomain:this.thirdFormGroup.controls.subdomain.value,
      
      skills:this.thirdFormGroup.controls.skill.value,
      
      role:this.thirdFormGroup.controls.RoleCtrl.value,
      
      chargePerHour:this.thirdFormGroup.controls.chargePerHour.value

    }
    console.log(providerData);
    this.registrationService.addServiceProvider(providerData);
    this.dialog.open(ModalSuccessComponent);
    this.router.navigateByUrl('/home');

  }
}