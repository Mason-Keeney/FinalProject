import { Pipe, PipeTransform } from '@angular/core';
import { Project } from '../models/project';

@Pipe({
  name: 'projectPresent'
})
export class ProjectPresentPipe implements PipeTransform {

  transform(original: any[], project: Project): any[] {
    let sorted: any[] = [];

    for (let i = 0; i < original.length; i++) {
      let element = original[i];
      let id = element.project.id;
      if (id === project.id)         {
        sorted.push(element);
      }
    }
  return sorted;
  }

}
