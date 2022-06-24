export class User {

  id: number | null;
  username: string|null;
  password: string|null;
  enabled: boolean;
  role: string | null;
  firstName: string | null;
  lastName: string | null;
  imageUrl: string | null;
  description: string | null;
  createdAt: Date | null;
  updatedAt: Date | null;
  lastLogin: Date | null;
  backgroundImageUrl: string | null;



  constructor(
    id: number | null = 0,
    username: string = '',
    password: string = '',
    enabled: boolean = true,
    role: string | null = '',
    firstName: string | null = '',
    lastName: string | null = '',
    imageUrl: string | null = '',
    description: string | null = '',
    createdAt: Date | null = null,
    updatedAt: Date | null = null,
    lastLogin: Date | null = null,
    backgroundImageUrl: string | null = ''

  ){
    this.id = id;
    this.username = username;
    this.password = password;
    this.enabled = enabled;
    this.role = role;
    this.firstName = firstName;
    this.lastName = lastName;
    this.imageUrl = imageUrl;
    this.description = description;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.lastLogin = lastLogin;
    this.backgroundImageUrl = backgroundImageUrl;
  }
}
