import { useState } from 'react';
import { Button } from './ui/button';
import { Card, CardContent } from './ui/card';
import { Badge } from './ui/badge';
import { Input } from './ui/input';
import { Textarea } from './ui/textarea';
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogTrigger } from './ui/dialog';
import { Heart, MessageCircle, Filter, Plus } from 'lucide-react';

export function PortfolioPage() {
  const [selectedFilter, setSelectedFilter] = useState('all');
  const [showTestimonialForm, setShowTestimonialForm] = useState(false);

  const portfolioItems = [
    {
      id: 1,
      title: 'Blonde Transformation',
      category: 'color',
      beforeImage: null,
      afterImage: null,
      description: 'Complete color transformation from dark brown to platinum blonde',
      likes: 24,
      comments: 8
    },
    {
      id: 2,
      title: 'Modern Layered Cut',
      category: 'cuts',
      beforeImage: null,
      afterImage: null,
      description: 'Fresh layered cut with face-framing highlights',
      likes: 18,
      comments: 5
    },
    {
      id: 3,
      title: 'Balayage Highlights',
      category: 'color',
      beforeImage: null,
      afterImage: null,
      description: 'Natural-looking balayage for a sun-kissed effect',
      likes: 32,
      comments: 12
    },
    {
      id: 4,
      title: 'Pixie Cut Makeover',
      category: 'cuts',
      beforeImage: null,
      afterImage: null,
      description: 'Bold pixie cut transformation for a modern look',
      likes: 15,
      comments: 4
    }
  ];

  const filters = [
    { id: 'all', label: 'All Work' },
    { id: 'cuts', label: 'Cuts' },
    { id: 'color', label: 'Color' },
    { id: 'styling', label: 'Styling' }
  ];

  const testimonials = [
    {
      id: 1,
      name: 'Sarah Johnson',
      text: 'Amazing work! I love my new hair color. The team is so professional and talented.',
      rating: 5,
      service: 'Hair Coloring'
    },
    {
      id: 2,
      name: 'Emma Wilson',
      text: 'Best haircut I\'ve ever had! They really listened to what I wanted and delivered perfectly.',
      rating: 5,
      service: 'Haircut & Styling'
    }
  ];

  const filteredItems = selectedFilter === 'all' 
    ? portfolioItems 
    : portfolioItems.filter(item => item.category === selectedFilter);

  return (
    <div className="min-h-screen bg-background pt-20 pb-8 px-4">
      <div className="mb-6">
        <h1 className="text-xl font-medium mb-4">Our Portfolio</h1>
        
        {/* Filter Buttons */}
        <div className="flex gap-2 overflow-x-auto pb-2">
          {filters.map((filter) => (
            <Button
              key={filter.id}
              variant={selectedFilter === filter.id ? "default" : "outline"}
              size="sm"
              onClick={() => setSelectedFilter(filter.id)}
              className="whitespace-nowrap"
            >
              {filter.label}
            </Button>
          ))}
        </div>
      </div>

      {/* Portfolio Grid */}
      <div className="grid grid-cols-1 gap-6 mb-8">
        {filteredItems.map((item) => (
          <Card key={item.id} className="overflow-hidden">
            <CardContent className="p-0">
              {/* Before/After Images */}
              <div className="grid grid-cols-2 gap-1">
                <div className="relative">
                  <div className="w-full h-48 bg-muted flex items-center justify-center">
                    <p className="text-muted-foreground text-sm text-center">Image will be added later</p>
                  </div>
                  <Badge className="absolute top-2 left-2 bg-black/70 text-white">
                    Before
                  </Badge>
                </div>
                <div className="relative">
                  <div className="w-full h-48 bg-muted flex items-center justify-center">
                    <p className="text-muted-foreground text-sm text-center">Image will be added later</p>
                  </div>
                  <Badge className="absolute top-2 left-2 bg-primary">
                    After
                  </Badge>
                </div>
              </div>
              
              {/* Content */}
              <div className="p-4">
                <div className="flex items-start justify-between mb-2">
                  <div>
                    <h3 className="font-medium">{item.title}</h3>
                    <p className="text-sm text-muted-foreground mt-1">
                      {item.description}
                    </p>
                  </div>
                  <Badge variant="secondary" className="capitalize">
                    {item.category}
                  </Badge>
                </div>
                
                {/* Engagement */}
                <div className="flex items-center gap-4 mt-3 pt-3 border-t">
                  <button className="flex items-center gap-1 text-sm text-muted-foreground hover:text-foreground">
                    <Heart className="h-4 w-4" />
                    {item.likes}
                  </button>
                  <button className="flex items-center gap-1 text-sm text-muted-foreground hover:text-foreground">
                    <MessageCircle className="h-4 w-4" />
                    {item.comments}
                  </button>
                </div>
              </div>
            </CardContent>
          </Card>
        ))}
      </div>

      {/* Testimonials Section */}
      <Card className="mb-6">
        <CardContent className="p-4">
          <div className="flex items-center justify-between mb-4">
            <h3 className="font-medium">Client Testimonials</h3>
            <Dialog open={showTestimonialForm} onOpenChange={setShowTestimonialForm}>
              <DialogTrigger asChild>
                <Button size="sm" variant="outline">
                  <Plus className="h-4 w-4 mr-1" />
                  Add Review
                </Button>
              </DialogTrigger>
              <DialogContent>
                <DialogHeader>
                  <DialogTitle>Leave a Testimonial</DialogTitle>
                </DialogHeader>
                <div className="space-y-4 mt-4">
                  <div>
                    <label className="block text-sm font-medium mb-1">Your Name</label>
                    <Input placeholder="Enter your name" />
                  </div>
                  <div>
                    <label className="block text-sm font-medium mb-1">Service Received</label>
                    <Input placeholder="e.g., Haircut & Styling" />
                  </div>
                  <div>
                    <label className="block text-sm font-medium mb-1">Your Review</label>
                    <Textarea 
                      placeholder="Tell us about your experience..."
                      rows={4}
                    />
                  </div>
                  <div className="flex gap-2">
                    <Button className="flex-1">Submit Review</Button>
                    <Button variant="outline" onClick={() => setShowTestimonialForm(false)}>
                      Cancel
                    </Button>
                  </div>
                </div>
              </DialogContent>
            </Dialog>
          </div>
          
          <div className="space-y-4">
            {testimonials.map((testimonial) => (
              <div key={testimonial.id} className="border rounded-lg p-4">
                <div className="flex items-start justify-between mb-2">
                  <div>
                    <h4 className="font-medium">{testimonial.name}</h4>
                    <p className="text-sm text-muted-foreground">{testimonial.service}</p>
                  </div>
                  <div className="flex">
                    {[...Array(testimonial.rating)].map((_, i) => (
                      <span key={i} className="text-yellow-400">★</span>
                    ))}
                  </div>
                </div>
                <p className="text-sm">{testimonial.text}</p>
              </div>
            ))}
          </div>
        </CardContent>
      </Card>
    </div>
  );
}