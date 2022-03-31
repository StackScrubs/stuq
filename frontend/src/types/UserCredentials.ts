export interface UserCredentials {
  email: string;
  password: string;
}


export class InvalidCredentialsError extends Error {}