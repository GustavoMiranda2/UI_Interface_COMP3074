import { useState } from 'react';
import { Button } from './ui/button';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from './ui/card';
import { Input } from './ui/input';
import { Label } from './ui/label';
import { RadioGroup, RadioGroupItem } from './ui/radio-group';
import { Calendar } from './ui/calendar';
import { ArrowLeft, Check } from 'lucide-react';

interface BookingScreenProps {
  onBack: () => void;
}

export function BookingScreen({ onBack }: BookingScreenProps) {
  const [step, setStep] = useState(1);
  const [selectedService, setSelectedService] = useState('');
  const [selectedDate, setSelectedDate] = useState<Date>();
  const [selectedTime, setSelectedTime] = useState('');
  const [customerInfo, setCustomerInfo] = useState({
    name: '',
    email: '',
    phone: ''
  });

  const services = [
    { id: 'haircut', name: 'Haircut', price: '$20', duration: '30-45 min' },
    { id: 'haircut-beard', name: 'Haircut & Beard', price: '$25', duration: '45-60 min' },
    { id: 'eyebrows', name: 'Eyebrows', price: '$5', duration: '15-20 min' }
  ];

  const getTimeSlotsForDate = (date: Date) => {
    const dayOfWeek = date.getDay(); // 0 = Sunday, 1 = Monday, etc.
    
    if (dayOfWeek >= 1 && dayOfWeek <= 5) {
      // Monday to Friday: 7:00 PM to 10:30 PM
      return ['7:00 PM', '7:30 PM', '8:00 PM', '8:30 PM', '9:00 PM', '9:30 PM', '10:00 PM', '10:30 PM'];
    } else if (dayOfWeek === 6) {
      // Saturday: 8:00 AM to 5:00 PM
      return [
        '8:00 AM', '8:30 AM', '9:00 AM', '9:30 AM', '10:00 AM', '10:30 AM', '11:00 AM', '11:30 AM',
        '12:00 PM', '12:30 PM', '1:00 PM', '1:30 PM', '2:00 PM', '2:30 PM', '3:00 PM', '3:30 PM',
        '4:00 PM', '4:30 PM', '5:00 PM'
      ];
    } else if (dayOfWeek === 0) {
      // Sunday: 8:00 AM to 12:00 PM
      return ['8:00 AM', '8:30 AM', '9:00 AM', '9:30 AM', '10:00 AM', '10:30 AM', '11:00 AM', '11:30 AM', '12:00 PM'];
    }
    return [];
  };

  const handleConfirmBooking = () => {
    // Here you would typically send the booking data to your backend
    alert('Booking confirmed! You will receive a confirmation email shortly.');
    onBack();
  };

  const getSelectedServiceDetails = () => {
    return services.find(s => s.id === selectedService);
  };

  if (step === 4) {
    const serviceDetails = getSelectedServiceDetails();
    return (
      <div className="min-h-screen bg-background pt-20 pb-8 px-4">
        <div className="flex items-center gap-3 mb-6">
          <Button variant="ghost" size="icon" onClick={() => setStep(3)}>
            <ArrowLeft className="h-5 w-5" />
          </Button>
          <h1 className="text-xl font-medium">Booking Confirmation</h1>
        </div>

        <Card className="mb-6">
          <CardHeader>
            <CardTitle className="flex items-center gap-2">
              <Check className="h-5 w-5 text-green-600" />
              Booking Summary
            </CardTitle>
          </CardHeader>
          <CardContent className="space-y-4">
            <div>
              <Label className="text-sm text-muted-foreground">Service</Label>
              <p className="font-medium">{serviceDetails?.name}</p>
              <p className="text-sm text-muted-foreground">{serviceDetails?.duration}</p>
            </div>
            <div>
              <Label className="text-sm text-muted-foreground">Date & Time</Label>
              <p className="font-medium">
                {selectedDate?.toLocaleDateString()} at {selectedTime}
              </p>
            </div>
            <div>
              <Label className="text-sm text-muted-foreground">Customer</Label>
              <p className="font-medium">{customerInfo.name}</p>
              <p className="text-sm text-muted-foreground">{customerInfo.email}</p>
              <p className="text-sm text-muted-foreground">{customerInfo.phone}</p>
            </div>
            <div>
              <Label className="text-sm text-muted-foreground">Price</Label>
              <p className="font-medium">{serviceDetails?.price}</p>
            </div>
          </CardContent>
        </Card>

        <div className="space-y-3">
          <Button onClick={handleConfirmBooking} className="w-full h-12">
            Confirm Booking
          </Button>
          <Button variant="outline" onClick={() => setStep(3)} className="w-full h-12">
            Go Back
          </Button>
        </div>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-background pt-20 pb-8 px-4">
      <div className="flex items-center gap-3 mb-6">
        <Button variant="ghost" size="icon" onClick={step === 1 ? onBack : () => setStep(step - 1)}>
          <ArrowLeft className="h-5 w-5" />
        </Button>
        <h1 className="text-xl font-medium">
          {step === 1 && 'Select Service'}
          {step === 2 && 'Choose Date & Time'}
          {step === 3 && 'Your Information'}
        </h1>
      </div>

      {step === 1 && (
        <div className="space-y-4">
          <RadioGroup value={selectedService} onValueChange={setSelectedService}>
            {services.map((service) => (
              <Card key={service.id} className={`cursor-pointer transition-colors ${
                selectedService === service.id ? 'border-primary bg-primary/5' : ''
              }`}>
                <CardContent className="p-4">
                  <div className="flex items-center space-x-3">
                    <RadioGroupItem value={service.id} id={service.id} />
                    <div className="flex-1">
                      <div className="flex items-start justify-between">
                        <Label htmlFor={service.id} className="cursor-pointer font-medium">
                          {service.name}
                        </Label>
                        <span className="font-medium text-primary">{service.price}</span>
                      </div>
                      <p className="text-sm text-muted-foreground mt-1">{service.duration}</p>
                    </div>
                  </div>
                </CardContent>
              </Card>
            ))}
          </RadioGroup>
          
          <Button 
            onClick={() => setStep(2)} 
            disabled={!selectedService}
            className="w-full h-12 mt-6"
          >
            Continue
          </Button>
        </div>
      )}

      {step === 2 && (
        <div className="space-y-6">
          <Card>
            <CardHeader>
              <CardTitle>Select Date</CardTitle>
            </CardHeader>
            <CardContent>
              <Calendar
                mode="single"
                selected={selectedDate}
                onSelect={setSelectedDate}
                disabled={(date) => date < new Date()}
                className="rounded-md border w-full"
              />
            </CardContent>
          </Card>

          {selectedDate && (
            <Card>
              <CardHeader>
                <CardTitle>Available Times</CardTitle>
                <CardDescription>
                  {selectedDate.toLocaleDateString()}
                </CardDescription>
              </CardHeader>
              <CardContent>
                <div className="grid grid-cols-3 gap-2">
                  {getTimeSlotsForDate(selectedDate).map((time) => (
                    <Button
                      key={time}
                      variant={selectedTime === time ? "default" : "outline"}
                      size="sm"
                      onClick={() => setSelectedTime(time)}
                      className="h-10"
                    >
                      {time}
                    </Button>
                  ))}
                </div>
              </CardContent>
            </Card>
          )}

          <Button 
            onClick={() => setStep(3)} 
            disabled={!selectedDate || !selectedTime}
            className="w-full h-12"
          >
            Continue
          </Button>
        </div>
      )}

      {step === 3 && (
        <div className="space-y-6">
          <Card>
            <CardHeader>
              <CardTitle>Contact Information</CardTitle>
              <CardDescription>
                We'll use this information to send you booking confirmations and reminders.
              </CardDescription>
            </CardHeader>
            <CardContent className="space-y-4">
              <div>
                <Label htmlFor="name">Full Name</Label>
                <Input
                  id="name"
                  type="text"
                  value={customerInfo.name}
                  onChange={(e) => setCustomerInfo({...customerInfo, name: e.target.value})}
                  placeholder="Enter your full name"
                  className="mt-1 h-12"
                />
              </div>
              <div>
                <Label htmlFor="email">Email Address</Label>
                <Input
                  id="email"
                  type="email"
                  value={customerInfo.email}
                  onChange={(e) => setCustomerInfo({...customerInfo, email: e.target.value})}
                  placeholder="Enter your email"
                  className="mt-1 h-12"
                />
              </div>
              <div>
                <Label htmlFor="phone">Phone Number</Label>
                <Input
                  id="phone"
                  type="tel"
                  value={customerInfo.phone}
                  onChange={(e) => setCustomerInfo({...customerInfo, phone: e.target.value})}
                  placeholder="Enter your phone number"
                  className="mt-1 h-12"
                />
              </div>
            </CardContent>
          </Card>

          <Button 
            onClick={() => setStep(4)} 
            disabled={!customerInfo.name || !customerInfo.email || !customerInfo.phone}
            className="w-full h-12"
          >
            Review Booking
          </Button>
        </div>
      )}
    </div>
  );
}