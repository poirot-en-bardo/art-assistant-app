import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-error-messages',
  templateUrl: './error-messages.component.html'
})
export class ErrorMessagesComponent implements OnInit {
  @Input() form: FormGroup;
  @Input() field: string;

  constructor() { }

  ngOnInit(): void {
  }
}
