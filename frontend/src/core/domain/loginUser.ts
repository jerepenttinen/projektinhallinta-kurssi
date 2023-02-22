
export default class LoginUser {
  _username: string | null = null;
  _firstname: string | null = null;
  _lastname: string | null = null;
  _email: string | null = null;
  _password: string | null = null;

  constructor(inputElements: any) {
    this.username = inputElements.usernameRef.current?.value;
    this.firstname = inputElements.firstNameRef.current?.value;
    this.lastname = inputElements.lastNameRef.current?.value;
    this.email = inputElements.emailRef.current?.value;
    this.password = inputElements.passwordRef.current?.value;
  }

  get username(): string | null {
    return this._username;
  }

  set username(value: string | null) {
    this._username = value;
  }

  get firstname(): string | null {
    return this._firstname;
  }

  set firstname(value: string | null) {
    this._firstname = value;
  }

  get lastname(): string | null {
    return this._lastname;
  }

  set lastname(value: string | null) {
    this._lastname = value;
  }

  get email(): string | null {
    return this._email;
  }

  set email(value: string | null) {
    this._email = value;
  }

  get password(): string | null {
    return this._password;
  }

  set password(value: string | null) {
    this._password = value;
  }

};