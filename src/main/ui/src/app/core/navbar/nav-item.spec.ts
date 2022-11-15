import { NavItem } from './nav-item';

describe('NavItem', () => {
  it('should create an instance', () => {
    let navItem: NavItem = {
      name: '',
      routerLink: '',
      href: '',
      ariaLabel: ''
    };
    expect(navItem).toBeTruthy();
  });
});
