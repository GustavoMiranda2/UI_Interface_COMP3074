import { useState } from 'react';
import { Button } from './ui/button';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from './ui/card';
import { Input } from './ui/input';
import { Textarea } from './ui/textarea';
import { Badge } from './ui/badge';
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogTrigger } from './ui/dialog';
import { ArrowLeft, Calendar, Clock, Tag, Plus } from 'lucide-react';

export function BlogPage() {
  const [selectedPost, setSelectedPost] = useState<number | null>(null);
  const [showCreateForm, setShowCreateForm] = useState(false);

  const blogPosts = [
    {
      id: 1,
      title: '5 Hair Care Tips for Summer',
      excerpt: 'Keep your hair healthy and vibrant during the hot summer months with these essential tips from our expert stylists.',
      content: `Summer can be tough on your hair. The combination of sun, heat, humidity, and pool chemicals can leave your hair dry, damaged, and dull. Here are our top 5 tips to keep your hair looking its best all summer long:

1. **Protect from UV Rays**: Just like your skin, your hair needs protection from harmful UV rays. Use hair products with SPF or wear a hat when spending extended time outdoors.

2. **Deep Condition Regularly**: Summer heat can strip moisture from your hair. Use a deep conditioning treatment once a week to restore hydration and keep your hair soft and manageable.

3. **Limit Heat Styling**: Give your hair a break from blow dryers and hot tools. Embrace natural textures and air-dry when possible.

4. **Rinse After Swimming**: Chlorine and salt water can be damaging to your hair. Always rinse your hair with fresh water immediately after swimming.

5. **Stay Hydrated**: What you put in your body affects your hair health. Drink plenty of water and eat foods rich in omega-3 fatty acids and vitamins.

Follow these tips and your hair will thank you! Book a consultation with our stylists for personalized summer hair care advice.`,
      image: null,
      author: 'Sarah Johnson',
      date: '2024-09-15',
      readTime: '5 min read',
      tags: ['Hair Care', 'Summer', 'Tips']
    },
    {
      id: 2,
      title: 'The Latest Hair Color Trends for Fall',
      excerpt: 'Discover the hottest hair color trends this fall season, from warm caramels to bold burgundies.',
      content: `Fall is the perfect time to refresh your look with a new hair color. This season, we're seeing some stunning trends that range from subtle and natural to bold and dramatic. Here are the top hair color trends for fall 2024:

**Warm Caramel Tones**: Rich, warm caramel shades are perfect for fall. These colors complement the season's earthy tones and provide a beautiful contrast against autumn fashion.

**Burgundy and Wine Shades**: For those who want to make a statement, deep burgundy and wine colors are incredibly popular this season. These rich reds add sophistication and drama to any look.

**Chocolate Brown**: Classic chocolate brown is making a comeback. It's a versatile color that works with all skin tones and is perfect for those who want a change without going too bold.

**Copper Highlights**: Adding copper highlights to your existing color can instantly warm up your look and give you that perfect fall vibe.

**Mushroom Blonde**: This cool-toned blonde with gray undertones is perfect for those who want something unique and modern.

Ready to try one of these trends? Book a color consultation with our expert colorists to find the perfect shade for you!`,
      image: null,
      author: 'Maria Garcia',
      date: '2024-09-10',
      readTime: '4 min read',
      tags: ['Hair Color', 'Trends', 'Fall']
    },
    {
      id: 3,
      title: 'How to Choose the Perfect Haircut for Your Face Shape',
      excerpt: 'Learn how to select a haircut that flatters your unique face shape and enhances your natural features.',
      content: `Choosing the right haircut can completely transform your look and boost your confidence. The key is understanding your face shape and selecting a style that enhances your best features. Here's our guide to finding the perfect cut:

**Round Face**: Add height and length with layered cuts, long bobs, or side-swept bangs. Avoid blunt cuts that end at the chin.

**Square Face**: Soften angular features with layers, waves, or side parts. Long layers and soft bangs work particularly well.

**Oval Face**: You're lucky – almost any style works! Experiment with different lengths and textures to find what you love most.

**Heart-Shaped Face**: Balance a wider forehead with chin-length bobs or layers that add volume at the jawline.

**Long Face**: Create the illusion of width with layers, waves, or blunt cuts. Bangs can also help shorten the appearance of your face.

Remember, these are guidelines, not rules! The most important thing is choosing a style that makes you feel confident and beautiful. Our stylists are experts at analyzing face shapes and recommending cuts that will work perfectly for you.`,
      image: null,
      author: 'Lisa Chen',
      date: '2024-09-05',
      readTime: '6 min read',
      tags: ['Haircuts', 'Face Shape', 'Styling']
    }
  ];

  const selectedPostData = selectedPost ? blogPosts.find(p => p.id === selectedPost) : null;

  if (selectedPost && selectedPostData) {
    return (
      <div className="min-h-screen bg-background pt-20 pb-8 px-4">
        <div className="flex items-center gap-3 mb-6">
          <Button variant="ghost" size="icon" onClick={() => setSelectedPost(null)}>
            <ArrowLeft className="h-5 w-5" />
          </Button>
          <h1 className="text-xl font-medium">Blog</h1>
        </div>

        <article className="max-w-2xl mx-auto">
          <div className="w-full h-64 bg-muted rounded-lg mb-6 flex items-center justify-center">
            <p className="text-muted-foreground">Image will be added later</p>
          </div>
          
          <div className="mb-6">
            <h1 className="text-2xl font-semibold mb-4">{selectedPostData.title}</h1>
            
            <div className="flex items-center gap-4 text-sm text-muted-foreground mb-4">
              <div className="flex items-center gap-1">
                <Calendar className="h-4 w-4" />
                {new Date(selectedPostData.date).toLocaleDateString()}
              </div>
              <div className="flex items-center gap-1">
                <Clock className="h-4 w-4" />
                {selectedPostData.readTime}
              </div>
              <span>By {selectedPostData.author}</span>
            </div>
            
            <div className="flex gap-2 mb-6">
              {selectedPostData.tags.map((tag) => (
                <Badge key={tag} variant="secondary">
                  {tag}
                </Badge>
              ))}
            </div>
          </div>
          
          <div className="prose prose-sm max-w-none">
            {selectedPostData.content.split('\n\n').map((paragraph, index) => (
              <p key={index} className="mb-4 leading-relaxed">
                {paragraph.includes('**') ? (
                  paragraph.split('**').map((part, i) => 
                    i % 2 === 1 ? <strong key={i}>{part}</strong> : part
                  )
                ) : paragraph}
              </p>
            ))}
          </div>
        </article>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-background pt-20 pb-8 px-4">
      <div className="flex items-center justify-between mb-6">
        <h1 className="text-xl font-medium">Beauty Blog</h1>
        
        <Dialog open={showCreateForm} onOpenChange={setShowCreateForm}>
          <DialogTrigger asChild>
            <Button size="sm">
              <Plus className="h-4 w-4 mr-1" />
              New Post
            </Button>
          </DialogTrigger>
          <DialogContent className="max-w-md">
            <DialogHeader>
              <DialogTitle>Create New Post</DialogTitle>
            </DialogHeader>
            <div className="space-y-4 mt-4">
              <div>
                <label className="block text-sm font-medium mb-1">Title</label>
                <Input placeholder="Enter post title" />
              </div>
              <div>
                <label className="block text-sm font-medium mb-1">Excerpt</label>
                <Textarea 
                  placeholder="Brief description of the post..."
                  rows={2}
                />
              </div>
              <div>
                <label className="block text-sm font-medium mb-1">Content</label>
                <Textarea 
                  placeholder="Write your blog post content..."
                  rows={6}
                />
              </div>
              <div>
                <label className="block text-sm font-medium mb-1">Tags</label>
                <Input placeholder="Enter tags separated by commas" />
              </div>
              <div>
                <label className="block text-sm font-medium mb-1">Image URL</label>
                <Input placeholder="Enter image URL (optional)" />
              </div>
              <div className="flex gap-2 pt-2">
                <Button className="flex-1">Publish Post</Button>
                <Button variant="outline" onClick={() => setShowCreateForm(false)}>
                  Cancel
                </Button>
              </div>
            </div>
          </DialogContent>
        </Dialog>
      </div>

      <div className="space-y-6">
        {blogPosts.map((post) => (
          <Card key={post.id} className="cursor-pointer hover:shadow-md transition-shadow">
            <CardContent 
              className="p-0"
              onClick={() => setSelectedPost(post.id)}
            >
              <div className="flex gap-4 p-4">
                <div className="w-24 h-24 bg-muted rounded-lg flex-shrink-0 flex items-center justify-center">
                  <p className="text-xs text-muted-foreground text-center">Image will be added later</p>
                </div>
                <div className="flex-1 min-w-0">
                  <h3 className="font-medium mb-2 line-clamp-2">{post.title}</h3>
                  <p className="text-sm text-muted-foreground mb-3 line-clamp-2">
                    {post.excerpt}
                  </p>
                  <div className="flex items-center gap-3 text-xs text-muted-foreground">
                    <div className="flex items-center gap-1">
                      <Calendar className="h-3 w-3" />
                      {new Date(post.date).toLocaleDateString()}
                    </div>
                    <div className="flex items-center gap-1">
                      <Clock className="h-3 w-3" />
                      {post.readTime}
                    </div>
                  </div>
                </div>
              </div>
              
              <div className="px-4 pb-4">
                <div className="flex gap-2 flex-wrap">
                  {post.tags.slice(0, 3).map((tag) => (
                    <Badge key={tag} variant="secondary" className="text-xs">
                      {tag}
                    </Badge>
                  ))}
                </div>
              </div>
            </CardContent>
          </Card>
        ))}
      </div>
    </div>
  );
}