import React from 'react';


interface HomeProps {
    // Add props if needed
}

const Home: React.FC<HomeProps> = () => {
    return (
        <div className="home-container">
            <header className="home-header">
                <h1>Welcome to Login Management</h1>
                <p>Your secure authentication platform</p>
            </header>

            <main className="home-main">
                <section className="features-section">
                    <h2>Features</h2>
                    <div className="features-grid">
                        <div className="feature-card">
                            <h3>Secure Login</h3>
                            <p>Advanced authentication with JWT tokens</p>
                        </div>
                        <div className="feature-card">
                            <h3>User Management</h3>
                            <p>Comprehensive user profile management</p>
                        </div>
                        <div className="feature-card">
                            <h3>Dashboard</h3>
                            <p>Personalized user dashboard experience</p>
                        </div>
                    </div>
                </section>

                <section className="cta-section">
                    <h2>Get Started</h2>
                    <div className="cta-buttons">
                        <button className="btn btn-primary">Sign In</button>
                        <button className="btn btn-secondary">Sign Up</button>
                    </div>
                </section>
            </main>

            <footer className="home-footer">
                <p>&copy; 2024 Login Management. All rights reserved.</p>
            </footer>
        </div>
    );
};

export default Home;