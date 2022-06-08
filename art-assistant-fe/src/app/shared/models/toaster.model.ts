export interface ToasterModel {
  messageCode: MESSAGE_COD;
  toasterTypeClass: ToasterClassEnum;
}

export enum ToasterClassEnum {
  ERROR= 'toaster--error',
  SUCCESS = 'toaster--succes',
  WARNING = 'toaster--warning'
}

export enum MESSAGE_COD {
  SUCCESS = 'SUCCESS',
  ERROR = 'ERROR',
}
