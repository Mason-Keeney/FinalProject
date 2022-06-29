import { Pipe, PipeTransform } from '@angular/core';
import { User } from '../models/user';

@Pipe({
  name: 'userPresent'
})
export class UserPresentPipe implements PipeTransform {

  transform(original: any[], user: User): any[] {
    let sorted: any[] = [];

      for (let i = 0; i < original.length; i++) {
        let element = original[i];
        let id = element.user.id;
        if (id == user.id)         {
          sorted.push(element);
        }
      }
    return sorted;
  }

}
