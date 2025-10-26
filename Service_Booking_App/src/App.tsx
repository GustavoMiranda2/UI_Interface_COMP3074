import { useState } from "react";
import { Navigation } from "./components/Navigation";
import { HomeScreen } from "./components/HomeScreen";
import { BookingScreen } from "./components/BookingScreen";
import { AdminDashboard } from "./components/AdminDashboard";
import { PortfolioPage } from "./components/PortfolioPage";
import { BlogPage } from "./components/BlogPage";

export default function App() {
  const [currentView, setCurrentView] = useState("home");
  const [isAdmin, setIsAdmin] = useState(false);

  const handleViewChange = (view: string) => {
    if (view === "admin") {
      setIsAdmin(true);
      setCurrentView("admin");
    } else if (view === "home") {
      setIsAdmin(false);
      setCurrentView("home");
    } else {
      setCurrentView(view);
    }
  };

  const handleBookNow = () => {
    setCurrentView("booking");
  };

  const handleBackToHome = () => {
    setCurrentView(isAdmin ? "admin" : "home");
  };

  return (
    <div className="min-h-screen bg-background">
      <Navigation
        currentView={currentView}
        onViewChange={handleViewChange}
        isAdmin={isAdmin}
      />

      {currentView === "home" && (
        <HomeScreen onBookNow={handleBookNow} />
      )}

      {currentView === "booking" && (
        <BookingScreen onBack={handleBackToHome} />
      )}

      {currentView === "admin" && <AdminDashboard />}

      {currentView === "portfolio" && <PortfolioPage />}

      {currentView === "blog" && <BlogPage />}
    </div>
  );
}