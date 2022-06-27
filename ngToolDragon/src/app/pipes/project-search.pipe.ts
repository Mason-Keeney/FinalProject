import { Pipe, PipeTransform } from '@angular/core';
import { Project } from '../models/project';

@Pipe({
  name: 'projectSearch'
})
export class ProjectSearchPipe implements PipeTransform {

  transform(projectList: Project[], search: string): Project[] {
    let sorted: Project[] = [];

    if (search == '') {
      sorted = projectList;
    } else {
      for (let i = 0; i < projectList.length; i++) {
        let project = projectList[i];
        if (project.description?.includes(search) || project.owner?.firstName?.includes(search) || project.owner?.lastName?.includes(search)) {
          sorted.push(project);
        }
      }
    }
    return sorted;
  }

}
