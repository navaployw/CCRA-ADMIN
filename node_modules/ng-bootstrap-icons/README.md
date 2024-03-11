## ng-bootstrap-icons
### Work In Progress.

### Description

This package allows you to use the [Bootstrap Icons](https://github.com/twbs/icons) in your angular applications. The icons are designed by Bootstrap.

### Demo
<h4>
  <a href="https://ng-bootstrap-icons.stackblitz.io">
    >> See live demo <<
  </a>  (right-click, open in new tab)
</h4>

### Usage

_1. Install the package_

```sh
npm install ng-bootstrap-icons
```

_2. Generate a module to host the icons you'll import_
 
```sh
ng generate module icons
```
  
_3. Import assets_ 

You need to import:
 - BootstrapIconsModule, as it contains the `<bi>` component
 - The SVGs that you need

We put this in IconsModule for modularity. See example below:


*file: icon.module.ts*
```ts  
import { NgModule } from '@angular/core';

import { BootstrapIconsModule } from 'ng-bootstrap-icons';
import { Alarm, App, Bookmark } from 'ng-bootstrap-icons/icons';

// Select some icons (use an object, not an array)
const icons = {
  Alarm,
  App,
  Bookmark
};

@NgModule({
  imports: [
    BootstrapIconsModule.pick(icons)
  ],
  exports: [
    BootstrapIconsModule
  ]
})
export class IconsModule { }

// NOTES:
// 1. We add BootstrapIconsModule to the 'exports', since the <bi> component will be used in templates of parent module
// 2. Don't forget to pick some icons using BootstrapIconsModule.pick({ ... })
```

_3. Use it in template_

After importing the _IconsModule_ in your feature or shared module, use the icons as follows:

```html
<bi name="alarm" class="someclass"></bi>
<bi name="app" style="color: green;"></bi>
<bi name="bookmark"></bi>
```



### Styling icons


```html
<bi name="heart" class="big fill-red"></bi>
```

```css
.big {
  height: 50px;
  width: 50px;
}

.fill-red {
  fill: red;
}
```
### FAQ

**1. Is there a way to pick all the icons at once?**

Yes, by importing `allIcons` from `'ng-bootstrap-icons/icons'`:

```ts
import { NgModule } from '@angular/core';

import { BootstrapIconsModule } from 'ng-bootstrap-icons';
import { allIcons } from 'ng-bootstrap-icons/icons';

@NgModule({
  imports: [
    BootstrapIconsModule.pick(allIcons)
  ],
  exports: [
    BootstrapIconsModule
  ]
})
export class IconsModule { }
```

However, keep in mind that by doing this, all icons will end up in your application bundle. While this may not be much of an issue for prototyping, it is not recommended for any application that you plan to release.

### License

MIT © [Gabriel Lopes](mailto:biutas@hotmail.com)