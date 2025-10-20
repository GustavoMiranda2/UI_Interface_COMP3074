import { Home, Calendar, User, Camera, FileText, Menu } from 'lucide-react';
import { Button } from './ui/button';
import { Sheet, SheetContent, SheetTrigger } from './ui/sheet';

interface NavigationProps {
  currentView: string;
  onViewChange: (view: string) => void;
  isAdmin?: boolean;
}

export function Navigation({ currentView, onViewChange, isAdmin }: NavigationProps) {
  const userMenuItems = [
    { id: 'home', label: 'Home', icon: Home },
    { id: 'booking', label: 'Book', icon: Calendar },
    { id: 'portfolio', label: 'Portfolio', icon: Camera },
    { id: 'blog', label: 'Blog', icon: FileText },
  ];

  const adminMenuItems = [
    { id: 'admin', label: 'Dashboard', icon: User },
    { id: 'portfolio', label: 'Portfolio', icon: Camera },
    { id: 'blog', label: 'Blog', icon: FileText },
  ];

  const menuItems = isAdmin ? adminMenuItems : userMenuItems;

  return (
    <div className="fixed top-0 left-0 right-0 z-50 bg-background border-b border-border">
      <div className="flex items-center justify-between p-4">
        <h1 className="text-lg font-medium">Hairstylist Booking</h1>
        
        <Sheet>
          <SheetTrigger asChild>
            <Button variant="ghost" size="icon">
              <Menu className="h-5 w-5" />
            </Button>
          </SheetTrigger>
          <SheetContent side="right" className="w-64">
            <div className="flex flex-col gap-4 mt-8">
              {menuItems.map((item) => {
                const Icon = item.icon;
                return (
                  <Button
                    key={item.id}
                    variant={currentView === item.id ? "default" : "ghost"}
                    className="justify-start gap-3 h-12"
                    onClick={() => onViewChange(item.id)}
                  >
                    <Icon className="h-5 w-5" />
                    {item.label}
                  </Button>
                );
              })}
              
              <div className="border-t border-border pt-4 mt-4">
                <Button
                  variant="ghost"
                  className="justify-start gap-3 h-12 w-full"
                  onClick={() => onViewChange(isAdmin ? 'home' : 'admin')}
                >
                  <User className="h-5 w-5" />
                  {isAdmin ? 'User View' : 'Admin View'}
                </Button>
              </div>
            </div>
          </SheetContent>
        </Sheet>
      </div>
    </div>
  );
}