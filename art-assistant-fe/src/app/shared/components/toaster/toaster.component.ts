import { Component, Input } from '@angular/core';
import { MESSAGES_MAP } from './toaster.config';
import { ToasterModel } from '../../models/toaster.model';

@Component({
  selector: 'app-toaster',
  templateUrl: './toaster.component.html',
  styleUrls: ['./toaster.component.scss']
})
export class ToasterComponent {
  public _toasterConfig: ToasterModel;
  @Input() set toasterConfig(toasterConfig: ToasterModel) {
    this._toasterConfig = toasterConfig;
    this.setDisplayTime();
  }
  displayToaster = false;
  MESSAGES_MAP = MESSAGES_MAP;

  private setDisplayTime(): void {
    this.displayToaster = true;
    setInterval(() => {
      this.displayToaster = false;
    }, 5000);
  }
}
