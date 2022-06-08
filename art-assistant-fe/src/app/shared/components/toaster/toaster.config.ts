import { MESSAGE_COD } from '../../models/toaster.model'

export const MESSAGES_MAP = new Map<MESSAGE_COD, string>([
  [ MESSAGE_COD.SUCCESS , 'The operation was successful'],
  [ MESSAGE_COD.ERROR , 'Something went wrong']
]);
