import { Action, Selector, State, StateContext } from '@ngxs/store';

import { Injectable } from '@angular/core';
import { ShowErrorToaster, ShowSuccessToaster } from './toaster.action';
import { ToasterClassEnum, ToasterModel } from '../../models/toaster.model';

export interface ToasterStateModel {
  toasterConfig?: ToasterModel;
}

const defaults = {
  toasterConfig: undefined
}

@State<ToasterStateModel>({
  name: 'toaster',
  defaults: defaults,
})
@Injectable()
export class ToasterState {
  constructor() {
  }

  @Selector()
  static getToasterConfig(state: ToasterStateModel) {
    return state.toasterConfig;
  }

  @Action(ShowErrorToaster)
  showErrorToaster({patchState}: StateContext<ToasterStateModel>, action: ShowErrorToaster) {
    patchState({
      toasterConfig: {
        messageCode: action.messageCode,
        toasterTypeClass: ToasterClassEnum.ERROR
      }
    })
  }

  @Action(ShowSuccessToaster)
  showSuccessToaster({patchState}: StateContext<ToasterStateModel>, action: ShowSuccessToaster) {
    patchState({
      toasterConfig: {
        messageCode: action.messageCode,
        toasterTypeClass: ToasterClassEnum.SUCCESS
      }
    })
  }
}
