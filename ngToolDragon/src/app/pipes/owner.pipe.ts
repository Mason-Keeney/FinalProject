import { User } from 'src/app/models/user';
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'owner'
})
export class OwnerPipe implements PipeTransform {

  transform(original: any[], user: User): any[] {
    let sorted: any[] = [];
      for (let i = 0; i < original.length; i++) {
        let element = original[i];
         if(element.owner.id === user.id) {
          sorted.push(element);
        }
      }
      return sorted;
    }

}
