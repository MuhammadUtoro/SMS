export interface KeycloakTokenParsed {
  preferred_username?: string;
  email?: string;
  given_name?: string;
  family_name?: string;

  realm_access?: {
    roles: string[];
  };

  ressource_access?: {
    [client: string]: {
      roles: string[];
    };
  };

  sub?: string;
}
