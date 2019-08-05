import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {FormControl} from '@angular/forms';
import { MatDialog } from '@angular/material';
import subdomain from 'src/assets/jsonfiles/data2.json';
import { RegistrationServiceService } from '../registration-service.service';
import { Router } from '@angular/router';
import { ModalSuccessComponent } from '../modal-success/modal-success.component';


@Component({
  selector: 'app-innovator-registrartion',
  templateUrl: './innovator-registrartion.component.html',
  styleUrls: ['./innovator-registrartion.component.css']
})
export class InnovatorRegistrartionComponent implements OnInit {
  domain = new FormControl();
  domainList: string[] = ['IT', 'TOURISM'];
  subdomain = new FormControl();
 
  skills = new FormControl();
  skillsList: string[] = ['c','java','python'];
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;
  thirdFormGroup: FormGroup;
  isOptional = true;

 skillList: string[] = [ 'c', 'java',];

 form: any = {};

errorMessage = '';
pass:string='';
conf:string;
  LoginService: any;
i:any;
subDomainList:any=[];


  constructor(private _formBuilder: FormBuilder,private dialog:MatDialog,private registrationService:RegistrationServiceService, private router: Router) { }
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
    skill:['']
  });
}

  onSubmit()
  {

    this.pass= this.secondFormGroup.controls.password.value;
    this.conf= this.secondFormGroup.controls.confirm.value;
  }
 
 register():void {
   console.log(this.form);
   let innovatorData={
   
      emailId:this.secondFormGroup.controls.email.value,
      password:this.secondFormGroup.controls.password.value,
      name:this.firstFormGroup.controls.FirstName.value,
      domain:this.secondFormGroup.controls.domainCtrl.value,
      subDomain:this.thirdFormGroup.controls.subdomain.value,
   }

   console.log(innovatorData);
   this.registrationService.addInnovatorProfile(innovatorData);
   this.dialog.open(ModalSuccessComponent);
   this.router.navigateByUrl('/home');


  }
}