import { Button } from './ui/button';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from './ui/card';
import { Scissors, UserCheck, Eye, Clock } from 'lucide-react';

interface HomeScreenProps {
  onBookNow: () => void;
}

export function HomeScreen({ onBookNow }: HomeScreenProps) {
  const services = [
    {
      id: 'haircut',
      name: 'Haircut',
      description: 'Professional haircuts for all hair types',
      icon: Scissors,
      price: '$20',
      duration: '30-45 min'
    },
    {
      id: 'haircut-beard',
      name: 'Haircut & Beard',
      description: 'Complete haircut and beard trim service',
      icon: UserCheck,
      price: '$25',
      duration: '45-60 min'
    },
    {
      id: 'eyebrows',
      name: 'Eyebrows',
      description: 'Professional eyebrow shaping and trimming',
      icon: Eye,
      price: '$5',
      duration: '15-20 min'
    }
  ];

  return (
    <div className="min-h-screen bg-background pt-20 pb-8 px-4">
      {/* Logo Section */}
      <div className="text-center mb-8">
        <div className="w-20 h-20 bg-primary rounded-full flex items-center justify-center mx-auto mb-4">
          <Scissors className="h-10 w-10 text-primary-foreground" />
        </div>
        <h1 className="text-2xl font-medium mb-2">Hairstylist Booking</h1>
        <p className="text-muted-foreground">Professional hair services</p>
      </div>

      {/* Services Section */}
      <div className="space-y-4 mb-8">
        <h2 className="text-xl font-medium mb-4">Our Services</h2>
        {services.map((service) => {
          const Icon = service.icon;
          return (
            <Card key={service.id} className="w-full">
              <CardHeader className="pb-3">
                <div className="flex items-start gap-3">
                  <div className="w-12 h-12 bg-accent rounded-lg flex items-center justify-center flex-shrink-0">
                    <Icon className="h-6 w-6 text-accent-foreground" />
                  </div>
                  <div className="flex-1 min-w-0">
                    <div className="flex items-start justify-between gap-2">
                      <CardTitle className="text-base">{service.name}</CardTitle>
                      <span className="text-sm font-medium text-primary whitespace-nowrap">
                        {service.price}
                      </span>
                    </div>
                    <CardDescription className="text-sm mt-1">
                      {service.description}
                    </CardDescription>
                    <div className="flex items-center gap-1 mt-2">
                      <Clock className="h-3 w-3 text-muted-foreground" />
                      <span className="text-xs text-muted-foreground">{service.duration}</span>
                    </div>
                  </div>
                </div>
              </CardHeader>
            </Card>
          );
        })}
      </div>

      {/* Book Now Button */}
      <div className="fixed bottom-6 left-4 right-4 z-40">
        <Button 
          onClick={onBookNow}
          className="w-full h-14 text-lg font-medium"
          size="lg"
        >
          Book Now
        </Button>
      </div>

      {/* Contact Info */}
      <div className="bg-muted rounded-lg p-4 mb-20">
        <h3 className="font-medium mb-2">Contact Us</h3>
        <div className="space-y-1 text-sm text-muted-foreground">
          <p>📍 230 Woolner Avenue</p>
          <p>✉️ erickhenriquecanada2909@gmail.com</p>
        </div>
        <div className="mt-3">
          <p className="text-sm">
            <span className="font-medium">Hours:</span>
          </p>
          <div className="text-sm text-muted-foreground mt-1 space-y-1">
            <p>Monday - Friday: 7:00 PM - 10:30 PM</p>
            <p>Saturday: 8:00 AM - 5:00 PM</p>
            <p>Sunday: 8:00 AM - 12:00 PM</p>
          </div>
        </div>
      </div>
    </div>
  );
}