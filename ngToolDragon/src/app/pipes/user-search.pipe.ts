import { User } from 'src/app/models/user';
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'userSearch'
})
export class UserSearchPipe implements PipeTransform {

  transform(userList: User[], search: string): User[] {
      let sorted: User[] = [];
      if (search == '') {
        sorted = userList;
      } else {
        for (let i = 0; i < userList.length; i++) {
          let user = userList[i];
          let id = user.id + "";
          if (
            user.firstName?.includes(search) ||
            user.lastName?.includes(search) ||
            user.username?.includes(search) ||
            id.includes(search))
            {
            sorted.push(user);
          }
        }
      }
      return sorted;
    }
}
