import ProductCard from './ProductCard';
export default function ProductGrid({ products, onAdd }) { if (!products.length) return <div className="empty-state"><h3>No pairs found.</h3><p>Try widening your search.</p></div>; return <div className="product-grid">{products.map((product) => <ProductCard key={product.id} product={product} onAdd={onAdd} />)}</div>; }
