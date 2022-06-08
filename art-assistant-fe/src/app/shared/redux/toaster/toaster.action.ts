import { MESSAGE_COD } from '../../models/toaster.model';

export class ShowErrorToaster {
  static readonly type = '[Toaster] Show Error';
  constructor(public messageCode = MESSAGE_COD.ERROR) {}
}

export class ShowSuccessToaster {
  static readonly type = '[Toaster] Show Success';
  constructor(public messageCode = MESSAGE_COD.SUCCESS) {}
}
