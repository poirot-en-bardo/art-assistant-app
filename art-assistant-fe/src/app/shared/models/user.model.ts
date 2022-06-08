export interface UserSignInModel {
  email: string,
  password: string
}

export interface UserSignUpModel {
  firstName: string,
  lastName: string,
  email: string,
  password: string,
  roles: string[]
}
