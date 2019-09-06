import {AuthoritiesResponse} from "./authorities-response";

export class JwtResponse {
  email: string;
  authorities: AuthoritiesResponse;
  tokenType: string;
  accessToken: string;
  userId: number;
}
